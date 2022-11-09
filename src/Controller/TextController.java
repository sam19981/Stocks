package controller;

import java.io.Console;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import view.IView;
import model.IModel;
import model.Portfolio;

/**
 * Controller class controls the flow of execution of the entire program and
 * is responsible for taking inputs form the user and passing it to the appropriate
 * models and its functions.
 */
public class TextController implements IController {
  private final Scanner in;
  private final IView view;
  private final IModel model;

  /**
   * Facilitates instantiation of the controller object.
   * @param model - data models required for
   *                the controller to represent
   *                user and perform operations on them.
   * @param in - InputStream object to to intialize scanner calss for
   *             taking inputs for the user.
   * @param view - view for displaying
   *             the prompts and user result of user interactions.
   */
  public TextController(IModel model, InputStream in, IView view) {
    this.model = model;
    this.view = view;
    this.in = new Scanner(in);
  }

  /**
   * Program execution flow begins here.
   *Takes input from the user and passes it to
   * the appropriate model for operations and
   * returns the result to user through view.
   */
  @Override
  public void connect() {
    boolean mainQuit = false;
    while (!mainQuit) {
      view.showPreLoginOptions();
      String option = in.nextLine().toUpperCase();
      boolean quit = false;
      switch (option) {
        case "N":
          while (!quit) {
            view.requestUsername();
            String username = in.nextLine();
            while (!model.checkValidUserName(username)) {
              view.pleaseUseAValidUserName();
              view.requestUsername();
              username = in.nextLine();
            }
            view.pleaseEnterString("password");
            String pass = fetchValidPassword();
            while (!model.checkValidPassword(pass)) {
              view.pleaseEnterAValidPassword();
              view.pleaseEnterString("password");
              pass = fetchValidPassword();
            }
            quit = true;
            view.showGreeting(username);
            view.showDataEntryOptions();
            getInputStyle(username, pass);
          }
          break;

        case "E":
          while (!quit) {
            view.requestUsername();
            String userName = in.nextLine();
            view.pleaseEnterString("password");
            String pass = fetchValidPassword();
            while (!model.checkValidPassword(pass)) {
              view.pleaseEnterAValidPassword();
              view.pleaseEnterString("password");
              pass = fetchValidPassword();
            }
            quit = setUser(userName, pass);
          }
          break;

        case "Q":
          mainQuit = true;
          break;
        default:
          view.showOptionError();
      }
    }
  }

  private boolean setUser(String userName, String pass) {
    int status = model.setUser(userName, pass);
    if (status >= 0) {
      view.showGreeting(userName);
      performUserOperation();
      return true;
    } else if (status == -1) {
      view.pleaseInputCorrectDetails("Name/Password");
    } else if (status == -2) {
      view.pleaseInputCorrectDetails("File");
    }
    return false;
  }

  private String fetchValidPassword() {
    String pass;
    Console console = System.console();
    if (console != null) {
      pass = new String(console.readPassword("Enter password: "));
    } else {
      pass = in.nextLine();
    }
    return pass;
  }

  private void getInputStyle(String username, String pass) {
    String style = in.nextLine().toUpperCase();
    boolean mainQuit = false;
    while (!mainQuit) {
      switch (style) {
        case "F":
          parseFromFile(username, pass);
          mainQuit = true;
          break;
        case "I":
          model.createUser(username, pass, "");
          getPortfolioInformation();
          getStocksInformation();
          view.showGreeting(username);
          performUserOperation();
          mainQuit = true;
          break;
        default:
          view.showOptionError();
          view.showDataEntryOptions();
          style = in.nextLine();
      }
    }
  }

  /**
   * This function uses the details the user has provided so far (including the file input).
   * It calls the Parser's read Class to read the details from the XML.
   * and store it in the user object.
   *
   * @param username - Name the user provides.
   * @param pass     - Pass provided by the user.
   */
  private void parseFromFile(String username, String pass) {
    boolean quit = false;
    String fileName;
    while (!quit) {
      view.fileInstructions();
      view.pleaseEnterString("file from which you want to input information");
      fileName = in.nextLine();
      if (model.checkValidFile(fileName)) {
        quit = true;
      } else {
        view.pleaseInputCorrectDetails("file from which you want to input information");
      }
      if (model.createUser(username, pass, fileName) >= 0) {
        quit &= setUser(username, pass);
      } else {
        quit = false;
      }
    }
  }

  /**
   * This function will fetch all the information about a portfolio that-.
   * - the user wants to add to his details.
   * The details include the number of portfolios, Portfolio name.
   */
  private void getPortfolioInformation() {
    boolean quit = false;
    while (!quit) {
      view.getNoPortfolios();
      String number = in.nextLine();
      float n = model.checkValidNaturalNumber(number);
      if ((n) != -1) {
        quit = true;
        view.getPortfolioNames();
        for (int i = 1; i <= n; i++) {
          view.getPortfolioNumber(String.valueOf(i));
          String pName = in.nextLine();
          while (model.addPortfolios(pName) == -1) {
            view.pleaseUseADifferentPortfolioName();
            view.getPortfolioNumber(String.valueOf(i));
            pName = in.nextLine();
          }
        }
      } else {
        view.pleaseInputCorrectDetails("Portfolio Number");
      }
    }
  }

  /**
   * This function fetches all the information that is needed before a Stock could be created.
   * then directs the Model to create the Stock.
   */
  private void getStocksInformation() {
    boolean mainQuit = false;
    String year = "";
    String month = "";
    String date = "";
    String quantity = "0";
    while (!mainQuit) {
      List<Portfolio> portfolios = model.getUserPortFolios();
      for (Portfolio p : portfolios) {
        if (p.getAllStocks().isEmpty()) {
          view.getStockforPortfolio(p.getPortfolioName());
          String number = in.nextLine();
          while (model.checkValidNaturalNumber(number) <= 0) {
            view.getStockforPortfolio(p.getPortfolioName());
            number = in.nextLine();
          }
          for (int i = 0; i < Integer.parseInt(number); i++) {
            view.pleaseEnterString("Stock Name");
            String sName = in.nextLine();

            boolean quit = false;
            while (!quit) {
              view.pleaseEnterString("quantity");
              quantity = in.nextLine();
              if (model.checkValidNaturalNumber(quantity) >= 0) {
                quit = true;
              } else {
                view.pleaseInputCorrectDetails("Quantity");
              }
            }
            boolean dateQuit = false;
            while (!dateQuit) {
              quit = false;
              while (!quit) {
                view.pleaseEnterString("purchase Date");
                date = in.nextLine();
                if (model.checkValidNaturalNumber(date) >= 0) {
                  quit = true;
                } else {
                  view.pleaseInputCorrectDetails("Date");
                }
              }
              quit = false;
              while (!quit) {
                view.pleaseEnterString("purchase Month");
                month = in.nextLine();
                if (model.checkValidNaturalNumber(month) >= 0) {
                  quit = true;
                } else {
                  view.pleaseInputCorrectDetails("Month");
                }
              }
              quit = false;
              while (!quit) {
                view.pleaseEnterString("purchase Year");
                year = in.nextLine();
                if (model.checkValidNaturalNumber(year) >= 0) {
                  quit = true;
                } else {
                  view.pleaseInputCorrectDetails("Year");
                }
              }
              if (model.checkValidDate(year, month, date) != null) {
                dateQuit = true;
              } else {
                view.pleaseInputCorrectDetails("Date");
              }
            }
            view.pleaseEnterString("stock symbol");
            String symbol = in.nextLine();
            while (model.validateStocksymbol(symbol) == 0) {
              view.pleaseEnterString("valid Symbol");
              symbol = in.nextLine();
            }
            model.addStock(p, model.createStock(sName, quantity,
                    date, month, year, symbol));
            mainQuit = true;
          }
        }
      }
    }
  }


  private void performUserOperation() {
    boolean mainQuit = false;
    while (!mainQuit) {
      view.showUserOperations();
      String operation = in.nextLine().toUpperCase();
      switch (operation) {
        case "S":
          view.displayPortfolios(model.getUserPortFolios());
          break;
        case "C":
          boolean quit = false;
          while (!quit) {
            view.displayPortfolios(model.getUserPortFolios());
            view.fetchPortfolioForComputation();
            String portfolioName = in.nextLine();
            while (portfolioName.equals("")) {
              view.pleaseUseADifferentPortfolioName();
              portfolioName = in.nextLine();
            }
            view.fetchDate();
            String date = in.nextLine();
            view.fetchMonth();
            String month = in.nextLine();
            view.fetchYear();
            String year = in.nextLine();
            float res = model.computePortfolioValues(portfolioName,
                    model.checkValidDate(year, month, date));
            if (res != -1) {
              quit = true;
              view.displayIndividualPortfolio(model.getUserPortFolios(), portfolioName);
              view.displayPortfolioResults(res);
            } else {
              view.pleaseInputCorrectDetails("Details");
            }
          }
          break;
        case "A":
          getPortfolioInformation();
          getStocksInformation();
          view.displayPortfolios(model.getUserPortFolios());
          break;
        case "L":
          mainQuit = true;
          break;
        default:
          view.showOptionError();
          break;
      }
    }
  }

}
