package integrationtest;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import controller.IController;
import controller.TextController;
import model.IModel;
import model.Model;
import view.IView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * The following class tests the functionality of the program-.
 * -as a whole by using end-end scenarios.
 * It also tests the functionality of the program with end-end scenarios.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationFunctionalityTest {
  String userInput;
  IModel model;
  ByteArrayInputStream bais;
  ByteArrayOutputStream baos;
  IView view;
  IController controller;

  @Before
  public void setup() {
    System.setIn(bais);
    baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    view = new TextView(System.out);
    model = new Model();
  }

  @Test
  public void testAGoForInitialQuit() {
    userInput = "Q\n";
    bais = new ByteArrayInputStream(userInput.getBytes());
    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

    assertEquals("\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "N: Are you a new user?\n" +
            "E: Are you an existing user?\n" +
            "Q: Quit the program\n" +
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForInvalidUsernamePassword() {
    userInput = "E\nSam199\nS845764\nSam19981\nsam56\nSam19981\nsam123\nS\nL\nQ";
    bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForInvalidInputs() {
    userInput = "x\nE\nSam19981\nsam123\n-10\nS\nQ\nL\nH\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testBGoForExistingShow() {
    String userInput = "E\nSam19981\nsam123\nS\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testCGoForExistingCompute() {
    String userInput = "E\nSam19981\nsam123\nC\nCollege Funds\n20\n3\n2020\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testDGoForExistingAddPortfolio() {
    String userInput = "E\nSam19981\nsam123\nA" +
            "\n1\nVacation Funds\n1\nGoogle\n100\n20\n3\n2020\nGOOG\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Please input the number of Stocks for Portfolio: Vacation Funds\n" +
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
            "Vacation Funds\n" +
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
            "Enter your choice: ", baos.toString());
  }

  @Test
  public void testEGoForAddNewPortfolioInvalidInputs() {
    String userInput = "E\nSam19981\nsam123\nA\n1\nCollege" +
            " Funds\nEmergency Funds\n0.9\n-10\n0\n1\nGoogle\n0.9" +
            "\n-10\n0\n100\n20\n3\n2020\nGOOOOG\nGOOG\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Please use a different Portfolio Name\n" +
            "Please enter the name of portfolio 1\n" +
            "Please input the number of Stocks for Portfolio: Emergency Funds\n" +
            "Please input the number of Stocks for Portfolio: Emergency Funds\n" +
            "Please input the number of Stocks for Portfolio: Emergency Funds\n" +
            "Please input the number of Stocks for Portfolio: Emergency Funds\n" +
            "Please enter the Stock Name\n" +
            "Please enter the quantity\n" +
            "Please input correct Quantity\n" +
            "Please enter the quantity\n" +
            "Please input correct Quantity\n" +
            "Please enter the quantity\n" +
            "Please input correct Quantity\n" +
            "Please enter the quantity\n" +
            "Please enter the purchase Date\n" +
            "Please enter the purchase Month\n" +
            "Please enter the purchase Year\n" +
            "Please enter the stock symbol\n" +
            "Please enter the valid Symbol\n" +
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
            "Vacation Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 100      | \n" +
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
            "Enter your choice: ", baos.toString());
  }

  @Test
  public void testNewUser() {

    String userInput = "N\nSank123\nSank123\nI\n0\n1\nsam\n0\n1\nsam\n1\n11\n1\n2020" +
                    "\nGOOG\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    IController controller6 = new TextController(model, bais, view);
    //log for mock model
    controller6.connect();

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
            "Hello Sank123!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "F: Do you wish to provide your information in form of a file?\n" +
            "I: Do you wish to input your Stock information step-by-step?\n" +
            "Enter your choice: How many Portfolios do you wish to hold?\n" +
            "Please enter the names of your portfolios\n" +
            "Please enter the name of portfolio 1\n" +
            "Please input the number of Stocks for Portfolio: sam\n" +
            "Please enter the Stock Name\n" +
            "Please enter the quantity\n" +
            "Please enter the purchase Date\n" +
            "Please enter the purchase Month\n" +
            "Please enter the purchase Year\n" +
            "Please enter the stock symbol\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sank123!\n" +
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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }


  @Test
  public void testNewUserWithInvalidInputs() {

    IModel mockModel = new Model();

    String userInput = String.format("x%sN%sSanka123%sSanka123%sI%s1%ssam%s1" +
                    "%ssam%s1%s11%s1%s2020" +
                    "%sGOOG%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(baos);
    System.setOut(printStream);
    IView view = new TextView(System.out);

    IController controller6 = new TextController(mockModel, bais, view);
    //log for mock model
    controller6.connect();

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
            "Hello Sanka123!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "F: Do you wish to provide your information in form of a file?\n" +
            "I: Do you wish to input your Stock information step-by-step?\n" +
            "Enter your choice: How many Portfolios do you wish to hold?\n" +
            "Please enter the names of your portfolios\n" +
            "Please enter the name of portfolio 1\n" +
            "Please input the number of Stocks for Portfolio: sam\n" +
            "Please enter the Stock Name\n" +
            "Please enter the quantity\n" +
            "Please enter the purchase Date\n" +
            "Please enter the purchase Month\n" +
            "Please enter the purchase Year\n" +
            "Please enter the stock symbol\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sanka123!\n" +
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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

  @Test
  public void testNewUserWithFileInput() {
    String userInput = "N\nSanka123\nSanka123\nF\nSam19981.txt\nL\nQ";
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);
    IController controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();
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
            "Hello Sanka123!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "F: Do you wish to provide your information in form of a file?\n" +
            "I: Do you wish to input your Stock information step-by-step?\n" +
            "Enter your choice: Please place the input file in the users folder\n" +
            "Please enter the file from which you want to input information\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Sanka123!\n" +
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
            "Enter your choice: ", baos.toString());
  }

  @Test
  public void testNewUserWithNewFile() {

    String userInput = String.format("x%sN%sRandom123%srandom123%sF%snoFile%sSam19981" +
                    ".txt%sS%sL%sQ" +
                    "%s1" +
                    "%s2020" +
                    "%sGOOG%sL%sQ",
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator(), System.lineSeparator(), System.lineSeparator(),
            System.lineSeparator());
    ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
    System.setIn(bais);

    controller = new TextController(model, bais, view);
    //log for mock model
    controller.connect();

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
            "Hello Random123!\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Menu: \n" +
            "F: Do you wish to provide your information in form of a file?\n" +
            "I: Do you wish to input your Stock information step-by-step?\n" +
            "Enter your choice: Please place the input file in the users folder\n" +
            "Please enter the file from which you want to input information\n" +
            "Please input correct file from which you want to input information\n" +
            "Please place the input file in the users folder\n" +
            "Please enter the file from which you want to input information\n" +
            "\n" +
            "-------------------\n" +
            "\n" +
            "Hello Random123!\n" +
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
            "Vacation Funds\n" +
            "------------------------------------------------\n" +
            "Stock Name      | Quantity | \n" +
            "------------------------------------------------\n" +
            "Google          | 100      | \n" +
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
            "Enter your choice: ", baos.toString()); //output of model transmitted correctly
  }

}