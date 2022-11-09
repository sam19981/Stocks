package model;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface acts as a doorway between the controller,
 * and the rest of the Stock, Portfolio, User implementation.
 */
public interface IModel {

  /**
   * Creates user with the given username, password and filename.
   * If the user opts to provide his input manually and not through a file.
   * We create a new file ourselves and associate it with him.
   *
   * @param username - Unique username of the user.
   * @param password - Password associated with the user.
   * @param fileName - File if any provided by the user.
   * @return - -1 or -2 if arguments provided are invalid. 0 if the user is successfully created.
   */
  int createUser(String username, String password, String fileName);

  /**
   * This method checks if the new user that is to be created already exists.
   *
   * @param username - Unique username of the user.
   * @return - True if the user exists, False otherwise.
   */
  boolean checkExistingUser(String username);

  /**
   * The following functions sets the user whose information is provided.
   * as the current user to be operated on.
   * Returns -1 if the details provided are invalid.
   *
   * @param username - Unique username of the user.
   * @param password - Unique password of the user.
   * @return - -1 if the user does not exist or cannot be set, 0 if the set was successful.
   */
  int setUser(String username, String password);

  /**
   * Fetches a list of all the portfolios that is associated with the user.
   *
   * @return - List of portfolios.
   */
  List<Portfolio> getUserPortFolios();

  /**
   * Computes the value of a given portfolio on a given date.
   * It internally computes the value of each of the stock under its belt.
   *
   * @param portfolioName - The name of the Portfolio whose value has to be computed.
   * @param d             - The date on which the value has to be computed.
   * @return - The value of the portfolio summed over all of its stocks.
   */
  float computePortfolioValues(String portfolioName, LocalDate d);

  /**
   * Checks if a date is valid or not, taking into consideration each of year, month and day.
   *
   * @param year  - Year of the date
   * @param month - Month of the date
   * @param day   - Day of the year
   * @return - LocalDate object if the date was valid else Null
   */
  LocalDate checkValidDate(String year, String month, String day);

  /**
   * Checks if the number is valid or not.
   * The number is asserted as valid only if the number is not negative, non-zero.
   * and does not have any decimal parts
   *
   * @param number - number whose value has to be checked.
   * @return - float value of the String passed as input if valid, -1 otherwise.
   */
  float checkValidNaturalNumber(String number);

  /**
   * Adds new portfolio under the user's belt with the given Portfolio name.
   *
   * @param portfolioName - Name of the new Portfolio.
   * @return - -1 if Portfolio already exists, 0 if the addition of portfolio was successful.
   */
  int addPortfolios(String portfolioName);

  /**
   * Create a new stock under the portfolio mentioned by the user if the provided inputs are valid.
   * Returns null if any of them is invalid.
   *
   * @param sName    - Name of the Stock.
   * @param quantity - Quantity (Shares) of the new Stock.
   * @param date     - Day of purchase.
   * @param month    - Month of purchase.
   * @param year     - year when purchased.
   * @param symbol   - Tick symbol of the Stock.
   * @return - Stock objected created if all inputs are valid else null.
   */
  Stock createStock(String sName, String quantity, String date, String month, String year,
                    String symbol);

  /**
   * Associates a given stock object with a Portfolio object.
   *
   * @param portfolio - Portfolio to be associated with.
   * @param stock - Stock to be added.
   */
  void addStock(Portfolio portfolio, Stock stock);

  /**
   * Checks if a given file exists, is of the right format and is not a directory.
   *
   * @param filename - Filename whose validity has to be checked.
   * @return - 0 if valid, -1 if not
   */
  boolean checkValidFile(String filename);

  /**
   * Checks if a username is valid.
   * For a username to be valid the following criteria must be met:.
   * - It has to be unique.
   * - Has to be longer than or equal to 5 but lesser than or equal to 30 characters.
   * - Has to be alphanumeric but '_' is the exception.
   *
   * @param username - Username to be checked validity for.
   * @return - True if valid, False if not.
   */
  boolean checkValidUserName(String username);

  /**
   * Checks if a Stock Symbol is valid or not from the set of Stock Symbols that is valid.
   *
   * @param symbol - Symbole to be validated.
   * @return - 1 if successfully validated, 0 otherwise
   */
  int validateStocksymbol(String symbol);

  /**
   * Checks if the password provided by the user conforms to all the below rules:.
   * - Is of length greater than or equal to length 5.
   * - Does not contain blank spaces in it.
   * - Has digits in it.
   *
   * @param password - Password provided by the new user.
   * @return - True if the password is valid, false otherwise.
   */
  boolean checkValidPassword(String password);

}
