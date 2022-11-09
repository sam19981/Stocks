package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import parsers.UserXmlReaderImpl;
import parsers.UserXmlWriterImpl;
import utilities.Utility;

/**
 *Model class acts as a delegator to all other data model classes, it talks to the
 * controller and call the appropriate model based on the input passed by the
 * controller.
 */
public class Model implements IModel {
  private final UserXmlWriterImpl write;
  private final HashSet<String> userNames;
  private User currentUser;
  private String fileLink;
  private final HashSet<String> symbols = new HashSet<>();

  /**
   * Default constructor initializes all the data.
   * necessary for starting up the program.
   */
  public Model() {
    write = new UserXmlWriterImpl();
    userNames = new HashSet<>();

    try {
      String cwd = System.getProperty("user.dir");
      Path path = Paths.get(cwd + "/users");
      Files.createDirectories(path);
    } catch (IOException e) {
      System.err.println("Failed to create directory!" + e.getMessage());
    }

    try (BufferedReader br = new BufferedReader(new FileReader("UserDetails.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        userNames.add(Arrays.asList(values).get(0)
                .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}" +
                        "\\p{Sc}\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]", ""));
      }
      List<String> symbolList = Utility.loadCsvData("stockDirectory/StockSymbols.csv");
      for (String v : symbolList) {
        String[] symbol = v.split(",");
        symbols.add(symbol[0]);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructor to initializes a new user in memory.
   * @param userVal - the new user to be initialized in the memory.
   */
  public Model(User userVal) {
    currentUser = userVal;
    write = new UserXmlWriterImpl();
    userNames = new HashSet<>();
  }

  private int loadData(String username, String password) {
    try {
      List<String> fileData = Utility.loadCsvData("UserDetails.csv");
      String line;
      for (String fileDatum : fileData) {
        line = fileDatum;
        String[] values = line.split(",");
        if (Arrays.asList(values).get(0)
                .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}\\p{Sc}" +
                        "\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]", "")
                .equals(username)) {
          if (Arrays.asList(values).get(1)
                  .replaceAll("[^\\p{L}\\p{N}\\p{Z}\\p{Sm}\\p{Sc}" +
                          "\\p{Sk}\\p{Pi}\\p{Pf}\\p{Pc}\\p{Mc}]", "")
                  .equals(password)) {
            fileLink = Arrays.asList(values).get(values.length - 1);
            UserXmlReaderImpl r = new UserXmlReaderImpl();
            User u = r.readData(fileLink, password);
            if (u == null) {
              return -2;
            }
            currentUser = u;
            return 0;
          }
        }
      }
    } catch (IOException e) {
      return -1;
    }
    return -3;
  }

  private int directLoadData(String username, String password) {
    UserXmlReaderImpl r = new UserXmlReaderImpl();
    User u = r.readData(fileLink, password);
    if (u == null) {
      return -2;
    }
    currentUser = u;
    return 0;
  }

  @Override
  public boolean checkExistingUser(String username) {
    return !userNames.contains(username);
  }

  @Override
  public int createUser(String username, String password, String fileName) {
    User user = UserImpl.createBuilder().setUserName(username).setPassword(password).create();
    if (user == null) {
      return -2;
    }
    if (userNames.contains(username)) {
      return -1;
    }
    userNames.add(username);
    int status = 0;
    if (fileName.isEmpty()) {
      File myObj = new File("users/" + username + "File.txt");
      try {
        if (myObj.createNewFile()) {
          fileName = username + "File.txt";
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    } else {
      fileLink = "users/" + fileName;
      status = directLoadData(username, password);
    }
    if (status == 0) {
      fileLink = "users/" + fileName;
      append("UserDetails.csv", username, password, "users/" + fileName);
      currentUser = user;
    } else if (status == -2) {
      userNames.remove(username);
    }
    return status;
  }

  @Override
  public int setUser(String username, String password) {
    if (userNames.contains(username)) {
      return loadData(username, password);
    }
    return -1;
  }

  private void append(String fileName, String username, String password, String userFile) {
    try {
      FileWriter fw = new FileWriter(fileName, true);
      PrintWriter out = new PrintWriter(fw);
      out.println();
      out.print(username + ",");
      out.print(password + ",");
      out.print(userFile);
      out.flush();
      out.close();
      fw.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public List<Portfolio> getUserPortFolios() {
    return currentUser.getAllPortfolios();
  }

  @Override
  public float computePortfolioValues(String portfolioName, LocalDate d) {
    return currentUser.computePortfolioValue(portfolioName, d);
  }

  @Override
  public LocalDate checkValidDate(String year, String month, String date) {
    int yearloc;
    int monthloc;
    int dateloc;
    try {
      yearloc = Integer.parseInt(year);
      if (yearloc < 1970 || yearloc > 2022) {
        return null;
      }
      monthloc = Integer.parseInt(month);
      if (monthloc < 1 || monthloc > 12) {
        return null;
      }
      dateloc = Integer.parseInt(date);
      if (dateloc < 1 || dateloc > 31) {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
    try {
      return LocalDate.of(yearloc, monthloc, dateloc);
    }
    catch(Exception e) {
      return null;
    }
  }

  @Override
  public float checkValidNaturalNumber(String number) {
    if (number.matches("-?\\d+(\\.\\d+)?")) {
      float res = Float.parseFloat(number);
      if (res <= 0) {
        return -1;
      }
      if (res % 1 != 0) {
        return -1;
      }
      return res;
    }
    return -1;
  }

  @Override
  public int addPortfolios(String portfolioName) {
    Portfolio p = PortfolioImpl.getBuilder().portfolioName(portfolioName).create();
    if (p != null) {
      return currentUser.addPortfolio(p);
    }
    return -1;
  }

  @Override
  public int validateStocksymbol(String symbol) {
    if (!symbols.contains(symbol)) {
      return 0;
    }
    return 1;
  }


  @Override
  public Stock createStock(String sName, String quantity,
                           String date, String month, String year, String symbol) {
    if (checkValidNaturalNumber(quantity) == -1 ||
            checkValidDate(year, month, date) == null || validateStocksymbol(symbol) == -1) {
      return null;
    }
    return StockImpl.getBuilder().stockName(sName)
            .quantity(Float.parseFloat(quantity))
            .purchaseDate(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month),
                    Integer.parseInt(date)))
            .stockSymbol(symbol).create();
  }

  @Override
  public void addStock(Portfolio portfolio, Stock stocks) {
    portfolio.addStock(stocks);
    try {
      write.writeData(fileLink, currentUser);
    } catch (ParserConfigurationException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public boolean checkValidFile(String filename) {
    File f = new File("users/" + filename);
    return f.exists() && !f.isDirectory() && (f.getName().toLowerCase().endsWith(".txt") ||
            f.getName().toLowerCase().endsWith(".xml"));
  }

  @Override
  public boolean checkValidUserName(String userName) {
    //DOC
    boolean valid = userName.length() >= 5 && userName.length() <= 30;
    if (valid) {
      for (int i = 0; i < userName.length(); i++) {
        char c = userName.charAt(i);
        valid = Character.isLetterOrDigit(c);
        if (!valid) {
          valid = c == '_';
          if (!valid) {
            break;
          }
        }
      }
    }
    if (valid) {
      valid = Character.isLetter(userName.charAt(0));
    }
    return valid & checkExistingUser(userName);
  }


  @Override
  public boolean checkValidPassword(String password) {
    if (!((password.length() >= 5)
            && (password.length() <= 15))) {
      return false;
    }

    // to check space
    if (password.contains(" ")) {
      return false;
    }

    //to check if password has numbers
    int count = 0;
    for (int i = 0; i <= 9; i++) {
      String str1 = Integer.toString(i);
      if (password.contains(str1)) {
        count = 1;
        break;
      }
    }
    return count != 0;
  }

}