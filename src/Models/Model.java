package Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import Parsers.UserXmlReaderImpl;
import Parsers.UserXmlWriterImpl;
import Utilities.Utility;

public class Model implements IModel {
  private final UserXmlWriterImpl write;
  private final List<User> users;
  private final HashSet<String> userNames;
  private User currentUser;

  private final HashSet<String> symbols = new HashSet<>();

  public Model() {
    write = new UserXmlWriterImpl();
    userNames = new HashSet<>();
    users = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("UserDetails.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        userNames.add(Arrays.asList(values).get(0)
                .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}\\p{Sc}\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]",""));
      }
      List<String>symbolList = Utility.loadCsvData("stockDirectory/StockSymbols.csv");
      for (String v : symbolList) {
        String[] symbol = v.split(",");
        symbols.add(symbol[0]);
    }} catch (IOException e) {
      throw new RuntimeException(e);
    }

/*
    Stock s1 = StockImpl.getBuilder().shareName("Google").purchaseValue(1000)
            .purchaseDate(LocalDate.of(2002, 1, 3))
            .quantity(10).stockSymbol("GOOG").create();
    Stock s2 = StockImpl.getBuilder().shareName("All").purchaseValue(1000)
            .purchaseDate(LocalDate.of(2022, 4, 3))
            .quantity(10).stockSymbol("ALL").create();
    List<Stock> s = new ArrayList<>();
    s.add(s1);
    s.add(s2);
    Portfolio p1 = PortfolioImpl.getBuilder().portfolioName("First_Portfolio").stocks(s).create();
    List<Stock> stocks2 = new ArrayList<>();
    stocks2.add(s1);
    Portfolio p2 = PortfolioImpl.getBuilder().portfolioName("Second_Portfolio").stocks(stocks2).create();
    List<Portfolio> p = new ArrayList<>();
    p.add(p1);
    p.add(p2);
    User u1 = UserImpl.CreateBuilder().setUserName("karthikjb10").setPassword("karthik123").addAllPortfolioList(p).create();
*/
    /*UserXmlReaderImpl r = new UserXmlReaderImpl();
    User u1 = r.readData("test.txt", );
    userNames.add(u1.getUserName());
    users.add(u1);*/
  }

  public int loadData(String username, String password) {
    try  {
      List<String> fileData = Utility.loadCsvData("UserDetails.csv");
      String line;
      for (String fileDatum : fileData) {
        line = fileDatum;
        String[] values = line.split(",");
        if (Arrays.asList(values).get(0)
                .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}\\p{Sc}\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]", "")
                .equals(username)) {
          if (Arrays.asList(values).get(1)
                  .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}\\p{Sc}\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]", "")
                  .equals(password)) {
            String fileLink = Arrays.asList(values).get(values.length - 1);
            UserXmlReaderImpl r = new UserXmlReaderImpl();
            currentUser = r.readData(fileLink, password);
          }
        }
      }
    } catch (IOException e) {
      return -1;
    }
    return 0;
  }

  @Override
  public int createUser(String username, String password) {
    User user = UserImpl.CreateBuilder().setUserName(username).setPassword(password).create();
    if(user==null){
      return -2;
    }
    if(userNames.contains(username)){
      return -1;
    }
    users.add(user);
    userNames.add(username);
    File myObj = new File("users/"+username+"File.txt");
    try {
      myObj.createNewFile();
    }
    catch(IOException e) {
      System.out.println(e.getMessage());
    }
    append("UserDetails.csv",username, password, "users/"+username+"File.txt");
    currentUser = user;
    return 0;
  }

  @Override
  public int setUser(String username, String password) {
    if(userNames.contains(username)){
      return loadData(username, password);
      //return 0;
    }
    for(User user: users) {
      if(user.getUserName().equals(username)) {
        if(user.encryptedPass().equals(password)) {
          currentUser = user;
          return 0;
        }
      }
    }
    return -1;
  }

  public void append(String fileName, String username, String password, String userFile) {
    try {
      FileWriter fw = new FileWriter(fileName, true);
      PrintWriter out = new PrintWriter(fw);
      out.println();
      out.print(username+",");
      out.print(password+",");
      out.print(userFile);
      out.flush();
      out.close();
      fw.close();
    }
    catch(IOException e) {
      System.out.println(e.getMessage());
  }
  }

  @Override
  public List<Portfolio> getUserPortFolios() {
    return currentUser.getAllPortfolios();
  }

  @Override
  public float computePortfolioValues(String PortfolioName, LocalDate d) {
    return currentUser.computePortfolioValue(PortfolioName, d);
  }

  @Override
  public LocalDate createValidDate(String year, String month, String date) {
    int y = Integer.parseInt(year);

    int m = Integer.parseInt(month);

    int d = Integer.parseInt(date);
    if(!Utility.checkValidDate(y,m,d))
    {
      return null;
    }
    return LocalDate.of(y, m, d);
  }

  @Override
  public int checkValidNumber(String number) {
    if(number.matches("-?\\d+(\\.\\d+)?")){
      return Integer.parseInt(number);
    }
    return -1;
  }

  @Override
  public void addPortfolios(String PortfolioName)  {
    Portfolio p = PortfolioImpl.getBuilder().portfolioName(PortfolioName).create();
    currentUser.addPortfolio(p);
  }

  public int validateStocksymbol(String symbol) {
      if (!symbols.contains(symbol)) {
        return 0;
      }
      return 1;
    }


  @Override
  public Stock createStock(String sName, String quantity, String date, String month, String year, String symbol) {
    return StockImpl.getBuilder().shareName(sName)
            .quantity(Float.parseFloat(quantity))
            .purchaseDate(LocalDate.of(Integer.parseInt(year),Integer.parseInt(month), Integer.parseInt(date)))
            .stockSymbol(symbol).create();
  }

  @Override
  public void addStock(Portfolio p, Stock A) {
    p.addStock(A);
    try {
      write.writeData("users/"+currentUser.getUserName()+"File.txt", currentUser);
    }
    catch(ParserConfigurationException e) {
      System.out.println(e.getMessage());
    }
  }


}