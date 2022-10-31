package Controller;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;

import Models.IModel;
import Models.Model;
import Models.Portfolio;
import Models.Stock;
import View.IView;
import View.TextView;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TextControllerTest {

  @Test
  public void testAGoForInitialQuit() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("Q%s",
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
     //log for mock model
    controller6.go();

    assertEquals("", log.toString()); //inputs reached the model correctly
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForInvalidUsernamePassword() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("E%sSam199%sS845764%sSam19981%ssam56%sSam19981%ssam123%sS%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.go();

    assertEquals("Password provided to checkValidPassword:S845764Username provided to setUser:Sam199\n" +
            "Password provided to setUser:S845764\n" +
            "Password provided to checkValidPassword:sam56Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam56\n" +
            "Password provided to checkValidPassword:sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Enter your unique username: Please enter the password\n" +
            "Please input correct Name/Password\n" +
            "\n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sam19981!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: These are your Portfolio details:\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "School Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Microfocus      | 29       | \n" +
            "Amazon          | 17       | \n" +
            "Port1\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 120      | \n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForInvalidInputs() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("x%sE%sSam19981%ssam123%s56%s-10%sS%sQ%sL%sH%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.go();

    assertEquals("Password provided to checkValidPassword:sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfolios", log.toString());
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Invalid option. Please try again.\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sam19981!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "Invalid option. Please try again.\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "Invalid option. Please try again.\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: These are your Portfolio details:\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "School Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Microfocus      | 29       | \n" +
            "Amazon          | 17       | \n" +
            "Port1\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 120      | \n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "Invalid option. Please try again.\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Invalid option. Please try again.\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForExistingShow() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("E%sSam19981%ssam123%sS%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.go();

    assertEquals("Password provided to checkValidPassword:sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sam19981!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: These are your Portfolio details:\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "School Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Microfocus      | 29       | \n" +
            "Amazon          | 17       | \n" +
            "Port1\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 120      | \n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testCGoForExistingCompute() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("E%sSam19981%ssam123%sC%sCollege Funds%s20%s3%s2020%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.go();

    assertEquals("Password provided to checkValidPassword:sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfoliosYear provided to createValidDate:2020\n" +
            "Month provided to createValidDate:3\n" +
            "Date provided to createValidDate:20\n" +
            "PortfolioName provided to computePortfolioValues:College Funds\n" +
            "LocalDate provided to computePortfolioValues:2020-03-20\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sam19981!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: These are your Portfolio details:\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "School Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Microfocus      | 29       | \n" +
            "Amazon          | 17       | \n" +
            "Port1\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 120      | \n" +
            "Which of the above Portfolio's value do you wish to see?\n" +
            "Please input the date for computation\n" +
            "Please input the month for computation\n" +
            "Please input the year for computation\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "------------------------------------------------\n" +
            "Total         | 3281.204\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testDGoForExistingAddPortfolio() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModel(log);

    String userInput = String.format("E%sSam19981%ssam123%sA%s1%sEmergency Funds%s1%sGoogle%s100%s20%s3%s2020%sGOOG%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.go();

    assertEquals("Password provided to checkValidPassword:sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Number provided to checkValidNumber:1\n" +
            "PortfolioName provided to addPortfolios:Emergency Funds\n" +
            "Called getUserPortfoliosNumber provided to checkValidNumber:1\n" +
            "Number provided to checkValidNumber:100\n" +
            "Number provided to checkValidNumber:20\n" +
            "Number provided to checkValidNumber:3\n" +
            "Number provided to checkValidNumber:2020\n" +
            "Year provided to createValidDate:2020\n" +
            "Month provided to createValidDate:3\n" +
            "Date provided to createValidDate:20\n" +
            "Symbol provided to validateStocksymbol: GOOGStock Name provided to createStock: Google\n" +
            "Quantity provided to createStock: 100\n" +
            "Date provided to createStock: 20\n" +
            "Month provided to createStock: 3\n" +
            "Value provided to createStock: 2020\n" +
            "Symbol provided to createStock: GOOG\n" +
            "Portfolio provided to addStock: Emergency Funds\n" +
            "Quantity provided to addStock: Google\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: \n" +
            "Enter your unique username: Please enter the password\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sam19981!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: How many Portfolios do you wish to hold?\n" +
            "Please enter the names of your portfolios\n" +
            "Please enter the name of portfolio 1\n" +
            "Please input the number of Stocks for Portfolio: Emergency Funds\n" +
            "Please enter the Stock Name\n" +
            "Please enter the quantity\n" +
            "Please enter the purchase Date\n" +
            "Please enter the purchase Month\n" +
            "Please enter the purchase Year\n" +
            "Please enter the stock symbol\n" +
            "These are your Portfolio details:\n" +
            "College Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 14       | \n" +
            "Apple           | 13       | \n" +
            "Microsoft       | 13       | \n" +
            "School Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Microfocus      | 29       | \n" +
            "Amazon          | 17       | \n" +
            "Port1\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 120      | \n" +
            "Emergency Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 100      | \n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "S: Do you want to see all the portfolios under your name?\n" +
            "C: Do you wish to compute the values of any portfolios?\n" +
            "A: Do you wish to add new portfolios?\n" +
            "L: Logout\n" +
            "Enter your choice: \n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ",baos.toString());
  }



  static class MockModel implements IModel {
    private final StringBuilder log;
    Model model;

    public MockModel(StringBuilder log) {
      this.log = log;
      model = new Model();
    }

    @Override
    public int directLoadData(String username, String password) {
      log.append("Username provided to directLoadData: ").append(username).append("\n");
      log.append("Password provided to directLoadData:").append(password).append("\n");
      return model.directLoadData(username, password);
    }

    @Override
    public boolean checkExistingUser(String username) {
      log.append("Username provided to checkExistingUser:").append(username).append("\n");
      return model.checkExistingUser(username);
    }

    @Override
    public int createUser(String username, String password, String fileName) {
      log.append("Username provided to createUser:").append(username).append("\n");
      log.append("Password provided to createUse:").append(password).append("\n");
      log.append("Filename provided to createUse:").append(fileName).append("\n");
      return model.createUser(username,password,fileName);
    }

    @Override
    public int setUser(String username, String password) {
      log.append("Username provided to setUser:").append(username).append("\n");
      log.append("Password provided to setUser:").append(password).append("\n");
      return model.setUser(username, password);
    }

    @Override
    public List<Portfolio> getUserPortFolios() {
      log.append("Called getUserPortfolios");
      return model.getUserPortFolios();
    }

    @Override
    public float computePortfolioValues(String PortfolioName, LocalDate d) {
      log.append("PortfolioName provided to computePortfolioValues:").append(PortfolioName).append("\n");
      log.append("LocalDate provided to computePortfolioValues:").append(d).append("\n");
      return model.computePortfolioValues(PortfolioName, d);
    }

    @Override
    public LocalDate createValidDate(String year, String month, String date) {
      log.append("Year provided to createValidDate:").append(year).append("\n");
      log.append("Month provided to createValidDate:").append(month).append("\n");
      log.append("Date provided to createValidDate:").append(date).append("\n");
      return model.createValidDate(year, month, date);
    }

    @Override
    public float checkValidNumber(String number) {
      log.append("Number provided to checkValidNumber:").append(number).append("\n");
      return model.checkValidNumber(number);
    }

    @Override
    public int addPortfolios(String PortfolioName) {
      log.append("PortfolioName provided to addPortfolios:").append(PortfolioName).append("\n");
      return model.addPortfolios(PortfolioName);
    }

    @Override
    public Stock createStock(String sName, String quantity, String date, String month, String value, String symbol) {
      log.append("Stock Name provided to createStock: ").append(sName).append("\n");
      log.append("Quantity provided to createStock: ").append(quantity).append("\n");
      log.append("Date provided to createStock: ").append(date).append("\n");
      log.append("Month provided to createStock: ").append(month).append("\n");
      log.append("Value provided to createStock: ").append(value).append("\n");
      log.append("Symbol provided to createStock: ").append(symbol).append("\n");
      return model.createStock(sName,quantity,date,month,value,symbol);
    }

    @Override
    public void addStock(Portfolio p, Stock A) {
      log.append("Portfolio provided to addStock: ").append(p.getPortfolioName()).append("\n");
      log.append("Quantity provided to addStock: ").append(A.getStockName()).append("\n");
      model.addStock(p, A);
    }

    @Override
    public int checkValidFile(String filename) {
      log.append("File provided to checkValidFile: ").append(filename);
      return model.checkValidFile(filename);
    }

    @Override
    public boolean checkValidUserName(String username) {
      log.append("Username provided to checkValidUserName: ").append(username);
      return model.checkValidUserName(username);
    }

    @Override
    public int validateStocksymbol(String symbol) {
      log.append("Symbol provided to validateStocksymbol: ").append(symbol);
      return model.validateStocksymbol(symbol);
    }

    @Override
    public boolean checkValidPassword(String password) {
      log.append("Password provided to checkValidPassword:").append(password);
      return true;
    }
  }
}