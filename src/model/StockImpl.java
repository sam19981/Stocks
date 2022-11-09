package model;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import connection.Connection;
import connection.ConnectionImpl;

/**
 * Class to store all the details of stocks
 * present in a user portfolio.
 */
public class StockImpl implements Stock {
  private final String stockName;
  private final LocalDate purchaseDate;
  private final float purchaseValue;
  private final float quantity;
  private final String stockSymbol;

  /**
   * initializes the stock with its details name,stocksymbol,purchase date and value on purchase
   * date.
   * @param name - name of the stock.
   * @param date -  date of purchase of the stock.
   * @param value - number of shares of the given stock bought
   * @param symbol - ticker symbol of the stock
   * @param pValue - value of stock during the purchase date.
   */
  private StockImpl(String name, LocalDate date, float value,
                    String symbol, float pValue) {
    stockName = name;
    purchaseDate = date;
    quantity = value;
    stockSymbol = symbol;
    purchaseValue = pValue;
  }

  /**
   * creates the stock object after setting all its fields.
   * @return - returns a new stock object with all its field set
   *           with default or user specified values.
   */
  public static CustomerBuilder getBuilder() {
    return new CustomerBuilder();
  }

  /**
   * builder to set all the values of the stock with default values or user
   * specified values without any errors.
   */
  public static class CustomerBuilder {
    private String stockName;
    private LocalDate purchaseDate;
    private float purchaseValue;
    private float quantity;
    private String stockSymbol;

    private CustomerBuilder() {
      stockName = "";
      purchaseDate = LocalDate.now().minusDays(1);
      quantity = 0;
      stockSymbol = "";
      purchaseValue = 0;
    }

    public CustomerBuilder stockName(String name) {
      this.stockName = name;
      return this;
    }

    public CustomerBuilder purchaseDate(LocalDate d) {
      this.purchaseDate = d;
      return this;
    }

    public CustomerBuilder purchaseValue(float v) {
      this.purchaseValue = v;
      return this;
    }

    public CustomerBuilder quantity(float q) {
      this.quantity = q;
      return this;
    }

    public CustomerBuilder stockSymbol(String symbol) {
      this.stockSymbol = symbol;
      return this;
    }

    /**
     * creates a StockImpl object after setting all its field using a builder.
     * @return - a new stockimpl object with all its fields set with default values
     *           or user specified values.
     */
    public StockImpl create() {
      if (stockName.equals("") || stockSymbol.equals("")) {
        return null;
      }
      return new StockImpl(stockName, purchaseDate, quantity, stockSymbol, purchaseValue);
    }
  }

  @Override
  public String getStockName() {
    return stockName;
  }

  @Override
  public float getQuantity() {
    return quantity;
  }

  @Override
  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  @Override
  public float getPurchaseValue() {
    try {
      if (!stockSymbol.isEmpty()) {
        return getValue(purchaseDate);
      } else {
        return purchaseValue;
      }
    }catch(Exception e) {
      return 0;
    }
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public StockImpl increaseQuantity(float i) {
    return StockImpl.getBuilder().stockName(stockName)
            .quantity(quantity + i).purchaseDate(purchaseDate).stockSymbol(stockSymbol).create();
  }

  @Override
  public float getValue(LocalDate d) {
    Connection c = new ConnectionImpl();
    StringBuilder output = new StringBuilder();
    try {
      InputStream apiData = c.fetch(stockSymbol, d);
      if (apiData == null) {
        throw new IllegalArgumentException("Invalid stock symbol/date provided");
      }
      int b;
      while ((b = apiData.read()) != -1) {
        output.append((char) b);
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    String[] res = output.toString().split(",");
    return Float.parseFloat(res[10]);
  }

}
