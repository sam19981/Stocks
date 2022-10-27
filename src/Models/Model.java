package Models;

import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import Parsers.ReaderTest;
import Parsers.WriterTest;

public class Model implements IModel {
  private final WriterTest write;
  private final List<User> users;
  private final HashSet<String> userNames;
  private User currentUser;

  public Model() {
    write = new WriterTest();
    userNames = new HashSet<>();
    users = new ArrayList<>();

    /*Stock s1 = StockImpl.getBuilder().shareName("Google").purchaseValue(1000)
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
    ReaderTest r = new ReaderTest();
    User u1 = r.readData("test.txt");
    userNames.add(u1.getUserName());
    users.add(u1);
  }

  @Override
  public int createUser(String username, String password) {
    User user = UserImpl.CreateBuilder().setUserName(username).setPassword(password).create();
    if(userNames.contains(username)){
      return -1;
    }
    users.add(user);
    userNames.add(username);
    currentUser = user;
    return 0;
  }

  @Override
  public int setUser(String username, String password) {
    for(User user: users) {
      if(user.getUserName().equals(username)) {
        if(Arrays.equals(user.encryptedPass(), user.encryptPass(password))) {
          currentUser = user;
          return 0;
        }
      }
    }
    return -1;
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
    if(y<1970 || y>2022) {
      return null;
    }
    int m = Integer.parseInt(month);
    if(m<1 || m>12) {
      return null;
    }
    int d = Integer.parseInt(date);
    if(d<1 || d>31) {
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
    try {
      write.writeData("test.txt", currentUser);
    }
    catch(ParserConfigurationException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public Stock createStock(String sName, String quantity, String date, String month, String year, String value, String symbol) {
    return StockImpl.getBuilder().shareName(sName)
            .quantity(Float.parseFloat(quantity))
            .purchaseDate(LocalDate.of(Integer.parseInt(year),Integer.parseInt(month), Integer.parseInt(date)))
            .purchaseValue(Float.parseFloat(value))
            .stockSymbol(symbol).create();
  }

  @Override
  public void addStock(Portfolio p, Stock A) {
    p.addStock(A);
    try {
      write.writeData("test.txt", currentUser);
    }
    catch(ParserConfigurationException e) {
      System.out.println(e.getMessage());
    }
  }


}