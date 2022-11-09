package model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Defines all the operations which can be done on a
 * users portfolio.
 */
public interface Portfolio {

  /**
   * returns the name of the current portfolio.
   * @return - returns the portfolio name
   */
  String getPortfolioName();

  /**
   * returns the list of all the stocks present in a portfolio.
   * @return - returns the list of all the stocks present in a given portfolio.
   *
   */
  ArrayList<Stock> getAllStocks();

  /**
   * Return the total worth of a portfolio at the given date
   * by computing and add all the stock values.
   * @param date - date on which the portfolio value needs to be computed.
   * @return - the total value of the portfolio.
   */
  float getPortfolioValue(LocalDate date);

  /**
   * Adds the given stock to the current portfolio.
   * @param stock - stocks to be added to the given portfolio.
   */
  void addStock(Stock stock);

  /**
   * Sell the given stock in the portfolio and compute the returns.
   * @param stock - stock to be sold in the portfolio.
   * @return - the profit or loss made by selling the given stock.
   */
  float sellStock(Stock stock);

  /**
   * sell all the stocks present in a portfolio.
   * @return - total profit or loss made by selling the all the stocks
   *           present in a portfolio.
   *
   */
  float sellAllStocks();

  /**
   * return the stock details which user wants to examine.
   * @param stockName - name of the stock which user wants to examine.
   * @return - return the stock object which user wants to examine.
   */

  Stock getStock(String stockName);


}
