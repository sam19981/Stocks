package controllertest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import controller.IController;
import controller.TextController;
import model.IModel;
import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;
import model.User;
import model.UserImpl;
import parsers.UserXmlReaderImpl;
import parsers.UserXmlWriterImpl;
import view.IView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * The following class makes use of a Mock Model to test the correct working of a controller.
 * It also tests the functionality of the program with end-end scenarios.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TextControllerTest {
  String userInput;
  StringBuilder log;
  IModel mockModel;
  ByteArrayInputStream bais;
  ByteArrayOutputStream baos;
  IView view;
  IController controller;

  @Before
  public void setup() {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    System.setIn(bais);
    baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    view = new TextView(System.out);

  }

  @Test
  public void testAGoForInitialQuit() {
    userInput = "Q\n";
    bais = new ByteArrayInputStream(userInput.getBytes());
    controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();
    assertEquals("", log.toString());
  }

  @Test
  public void testBGoForInvalidUsernamePassword() {
    userInput = "E\nSam19981\nsam123\nS\nL\nQ";
    bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("Password provided to checkValidPassword:" +
            "sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
  }

  @Test
  public void testCGoForExistingCompute() {
    String userInput = "E\nSam19981\nsam123\nC\nCollege Funds\n20\n3\n2020\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("Password provided to checkValidPassword:" +
            "sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Called getUserPortfoliosYear provided to createValidDate:2020\n" +
            "Month provided to createValidDate:3\n" +
            "Date provided to createValidDate:20\n" +
            "PortfolioName provided to computePortfolioValues:College Funds\n" +
            "LocalDate provided to computePortfolioValues:2020-03-20\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
  }

  @Test
  public void testDGoForExistingAddPortfolio() {
    String userInput = "E\nSam19981\nsam123\nA" +
            "\n1\nVacation Funds\n1\nGoogle\n100\n20\n3\n2020\nGOOG\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("Password provided to checkValidPassword:" +
            "sam123Username provided to setUser:Sam19981\n" +
            "Password provided to setUser:sam123\n" +
            "Number provided to checkValidNumber:1\n" +
            "PortfolioName provided to addPortfolios:Vacation Funds\n" +
            "Called getUserPortfoliosNumber provided to checkValidNumber:1\n" +
            "Number provided to checkValidNumber:100\n" +
            "Number provided to checkValidNumber:20\n" +
            "Number provided to checkValidNumber:3\n" +
            "Number provided to checkValidNumber:2020\n" +
            "Year provided to createValidDate:2020\n" +
            "Month provided to createValidDate:3\n" +
            "Date provided to createValidDate:20\n" +
            "Symbol provided to validateStocksymbol: GOOG" +
            "Stock Name provided to createStock: Google\n" +
            "Quantity provided to createStock: 100\n" +
            "Date provided to createStock: 20\n" +
            "Month provided to createStock: 3\n" +
            "Year provided to createStock: 2020\n" +
            "Symbol provided to createStock: GOOG\n" +
            "Portfolio provided to addStock: Vacation Funds\n" +
            "Quantity provided to addStock: Google\n" +
            "Called getUserPortfolios", log.toString()); //inputs reached the model correctly
  }

  @Test
  public void testNewUser() {
    String userInput = "N\nSank123\nSank123\nI\n1\nsam\n1\nsam\n1\n11\n1\n2020\nGOOG\nL\nQ\n";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("Username provided to checkValidUserName: Sank123" +
            "Password provided to checkValidPassword:Sank123" +
            "Username provided to createUser:Sank123\n" +
            "Password provided to createUse:Sank123\n" +
            "Filename provided to createUse:\n" +
            "Number provided to checkValidNumber:1\n" +
            "PortfolioName provided to addPortfolios:sam\n" +
            "Called getUserPortfoliosNumber provided to checkValidNumber:1\n" +
            "Number provided to checkValidNumber:1\n" +
            "Number provided to checkValidNumber:11\n" +
            "Number provided to checkValidNumber:1\n" +
            "Number provided to checkValidNumber:2020\n" +
            "Year provided to createValidDate:2020\n" +
            "Month provided to createValidDate:1\n" +
            "Date provided to createValidDate:11\n" +
            "Symbol provided to validateStocksymbol: GOOG" +
            "Stock Name provided to createStock: sam\n" +
            "Quantity provided to createStock: 1\n" +
            "Date provided to createStock: 11\n" +
            "Month provided to createStock: 1\n" +
            "Year provided to createStock: 2020\n" +
            "Symbol provided to createStock: GOOG\n" +
            "Portfolio provided to addStock: sam\n" +
            "Quantity provided to addStock: sam\n", log.toString());
  }

  @Test
  public void testNewUserWithFileInput() {
    String userInput = "N\nSanka123\nSanka123\nF\nSam19981.txt\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    IController controller = new TextController(mockModel, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("Username provided to checkValidUserName: Sanka123" +
            "Password provided to checkValidPassword:Sanka123" +
            "File provided to checkValidFile: Sam19981.txt" +
            "Username provided to createUser:Sanka123\n" +
            "Password provided to createUse:Sanka123\n" +
            "Filename provided to createUse:Sam19981.txt\n" +
            "Username provided to setUser:Sanka123\n" +
            "Password provided to setUser:Sanka123\n", log.toString());
  }


  /**
   * The following is the mock implementation of the Model that the Controller interacts with.
   * By having logging statements for each of the methods that the Controller calls.
   * We can fetch the inputs provided to each of the methods in the Model.
   * and verify that there are no changes.
   */
  static class MockModel implements IModel {
    private final StringBuilder log;
    private final HashSet<String> userNames;
    private final UserXmlWriterImpl write;
    private String fileLink;
    private User currentUser;

    /**
     * The following constructor instantiates the logger and also creates a model object.
     *
     * @param log -  It logs all the input passed by the controller to check
     *            if the values passed by the controlled are being passed to
     *            model without any modification.
     */
    public MockModel(StringBuilder log) {
      write = new UserXmlWriterImpl();
      this.log = log;
      userNames = new HashSet<>();
    }


    @Override
    public boolean checkExistingUser(String username) {
      log.append("Username provided to checkExistingUser:").append(username).append("\n");
      return true;
    }

    @Override
    public int createUser(String username, String password, String fileName) {
      log.append("Username provided to createUser:").append(username).append("\n");
      log.append("Password provided to createUse:").append(password).append("\n");
      log.append("Filename provided to createUse:").append(fileName).append("\n");
      User user = UserImpl.createBuilder().setUserName(username).setPassword(password).create();
      userNames.add(username);
      File myObj = new File("users/" + username + "File.txt");
      try {
        if (myObj.createNewFile()) {
          fileName = username + "File.txt";
        }
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
      append(username, password, "users/" + fileName);
      currentUser = user;
      return 0;
    }

    private void append(String username, String password, String userFile) {
      try {
        FileWriter fw = new FileWriter("UserDetails.csv", true);
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
    public int setUser(String username, String password) {
      log.append("Username provided to setUser:").append(username).append("\n");
      log.append("Password provided to setUser:").append(password).append("\n");
      UserXmlReaderImpl r = new UserXmlReaderImpl();
      fileLink = "users/Sam19981.txt";
      currentUser = r.readData(fileLink, password);
      return 0;
    }

    @Override
    public List<Portfolio> getUserPortFolios() {
      log.append("Called getUserPortfolios");
      return currentUser.getAllPortfolios();
    }

    @Override
    public float computePortfolioValues(String portfolioName, LocalDate d) {
      log.append("PortfolioName provided to computePortfolioValues:")
              .append(portfolioName).append("\n");
      log.append("LocalDate provided to computePortfolioValues:").append(d).append("\n");
      return currentUser.computePortfolioValue(portfolioName, d);
    }

    @Override
    public LocalDate checkValidDate(String year, String month, String date) {
      log.append("Year provided to createValidDate:").append(year).append("\n");
      log.append("Month provided to createValidDate:").append(month).append("\n");
      log.append("Date provided to createValidDate:").append(date).append("\n");
      return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
    }

    @Override
    public float checkValidNaturalNumber(String number) {
      log.append("Number provided to checkValidNumber:").append(number).append("\n");
      return Float.parseFloat(number);
    }

    @Override
    public int addPortfolios(String portfolioName) {
      log.append("PortfolioName provided to addPortfolios:").append(portfolioName).append("\n");
      Portfolio p = PortfolioImpl.getBuilder().portfolioName(portfolioName).create();
      return currentUser.addPortfolio(p);
    }

    @Override
    public Stock createStock(String sName, String quantity,
                             String date, String month, String year, String symbol) {
      log.append("Stock Name provided to createStock: ").append(sName).append("\n");
      log.append("Quantity provided to createStock: ").append(quantity).append("\n");
      log.append("Date provided to createStock: ").append(date).append("\n");
      log.append("Month provided to createStock: ").append(month).append("\n");
      log.append("Year provided to createStock: ").append(year).append("\n");
      log.append("Symbol provided to createStock: ").append(symbol).append("\n");
      return StockImpl.getBuilder().stockName(sName)
              .quantity(Float.parseFloat(quantity))
              .purchaseDate(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month),
                      Integer.parseInt(date)))
              .stockSymbol(symbol).create();
    }

    @Override
    public void addStock(Portfolio portfolio, Stock stock) {
      log.append("Portfolio provided to addStock: ")
              .append(portfolio.getPortfolioName()).append("\n");
      log.append("Quantity provided to addStock: ").append(stock.getStockName()).append("\n");
      portfolio.addStock(stock);
      fileLink = "users/Sam19981.txt";
      try {
        write.writeData(fileLink, currentUser);
      } catch (ParserConfigurationException e) {
        System.out.println(e.getMessage());
      }
    }

    @Override
    public boolean checkValidFile(String filename) {
      log.append("File provided to checkValidFile: ").append(filename);
      return true;
    }

    @Override
    public boolean checkValidUserName(String username) {
      log.append("Username provided to checkValidUserName: ").append(username);
      return true;
    }

    @Override
    public int validateStocksymbol(String symbol) {
      log.append("Symbol provided to validateStocksymbol: ").append(symbol);
      return -1;
    }

    @Override
    public boolean checkValidPassword(String password) {
      log.append("Password provided to checkValidPassword:").append(password);
      return true;
    }
  }
}