package view;

import java.io.PrintStream;
import java.util.List;

import model.Portfolio;
import model.Stock;

/**
 *  Contains all the functions essential for displaying the results
 *  of user operations on models
 *  and prompts to guide the user to use the program.
 *  The functions the class does not take any argument and does not
 *  perform any operations and
 *  other than displaying text and prompts
 *  given by the controller.
 */
public class TextView implements IView {
  private final PrintStream out;

  public TextView(PrintStream out) {
    this.out = out;
  }

  /**
   * displays the prompts for the user to follow while
   * logging in.
   */
  public void showPreLoginOptions() {
    //print the UI
    createSpace();
    out.println("Menu: ");
    out.println("N: Are you a new user?");
    out.println("E: Are you an existing user?");
    out.println("Q: Quit the program");
    out.print("Enter your choice: ");
  }

  public void requestUsername() {
    out.print("\nEnter your unique username: ");
  }

  @Override
  public void showDataEntryOptions() {
    createSpace();
    out.println("Menu: ");
    out.println("F: Do you wish to provide your information in form of a file?");
    out.println("I: Do you wish to input your Stock information step-by-step?");
    out.print("Enter your choice: ");
  }

  public void showOptionError() {
    out.print("\nInvalid option. Please try again.");
  }

  @Override
  public void getNoPortfolios() {
    out.println("How many Portfolios do you wish to hold?");
  }

  @Override
  public void showUserOperations() {
    createSpace();
    out.println("Menu: ");
    out.println("S: Do you want to see all the portfolios under your name?");
    out.println("C: Do you wish to compute the values of any portfolios?");
    out.println("A: Do you wish to add new portfolios?");
    out.println("L: Logout");
    out.print("Enter your choice: ");
  }

  @Override
  public void displayPortfolios(List<Portfolio> portfolio) {
    out.println("These are your Portfolio details:");
    for (Portfolio p : portfolio) {
      printStocksForPortfolio(p);
    }
  }

  @Override
  public void displayIndividualPortfolio(List<Portfolio> portfolios, String portfolioName) {
    for (Portfolio p : portfolios) {
      if (p.getPortfolioName().equals(portfolioName)) {
        printStocksForPortfolio(p);
      }
    }
  }

  private void printStocksForPortfolio(Portfolio p) {
    out.println(p.getPortfolioName());
    List<Stock> stocks = p.getAllStocks();
    out.println("------------------------------------------------");
    out.println("Stock Name     " + " | " + "Quantity" + " | ");
    out.println("------------------------------------------------");
    for (Stock s : stocks) {
      out.println(String.format("%-15s", s.getStockName()) + " | "
              + String.format("%-8s", (int) s.getQuantity()) + " | ");
    }
  }


  @Override
  public void displayPortfolioResults(float result) {
    out.println("------------------------------------------------");
    out.println("Total" + String.format("%22s", "| " + result));
  }

  @Override
  public void displayAllPortfolioNames(List<Portfolio> portfolios) {
    for (Portfolio p : portfolios) {
      System.out.println(p.getPortfolioName());
    }
  }

  public void fetchPortfolioForComputation() {
    out.println("Which of the above Portfolio's value do you wish to see?");
  }

  @Override
  public void fetchDate() {
    out.println("Please input the date for computation");
  }

  @Override
  public void fetchMonth() {
    out.println("Please input the month for computation");
  }

  @Override
  public void pleaseInputCorrectDetails(String d) {
    out.println("Please input correct " + d);
  }

  @Override
  public void pleaseUseAValidUserName() {
    out.println("Please use a valid User Name");
  }

  @Override
  public void pleaseUseADifferentPortfolioName() {
    out.println("Please use a different Portfolio Name");
  }

  @Override
  public void fileInstructions() {
    out.println("Please place the input file in the users folder");
  }

  @Override
  public void showGreeting(String username) {
    createSpace();
    out.println("Hello " + username + "!");
  }

  @Override
  public void pleaseEnterAValidPassword() {
    out.println("Please enter a valid password");
  }

  @Override
  public void fetchYear() {
    out.println("Please input the year for computation");
  }

  @Override
  public void getPortfolioNames() {
    out.println("Please enter the names of your portfolios");
  }

  @Override
  public void getPortfolioNumber(String n) {
    out.println("Please enter the name of portfolio " + n);
  }

  @Override
  public void getStockforPortfolio(String pName) {
    out.println("Please input the number of Stocks for Portfolio: " + pName);
  }

  @Override
  public void pleaseEnterString(String s) {
    out.println("Please enter the " + s);
  }

  private void createSpace() {
    out.println();
    out.println("-------------------");
    out.println();
  }

}
