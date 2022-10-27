package Models;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;

import Connections.Connection;
import Connections.ConnectionImpl;

public class StockImpl implements Stock {
  private final String shareName;
  private final LocalDate purchaseDate;
  private final float purchaseValue;
  private final float quantity;
  private final String stockSymbol;

  private StockImpl(String name, LocalDate date, float value, float quant,
                    String symbol) {
    shareName = name;
    purchaseDate = date;
    purchaseValue = value;
    quantity = quant;
    stockSymbol = symbol;
  }

  public static CustomerBuilder getBuilder() {
    return new CustomerBuilder();
  }

  public static class CustomerBuilder {
    private String shareName;
    private LocalDate purchaseDate;
    private float purchaseValue;
    private float quantity;
    private String stockSymbol;

    private CustomerBuilder() {
      shareName = "";
      purchaseDate = LocalDate.now();
      purchaseValue = 0;
      quantity = 0;
      stockSymbol = "";
    }

    public CustomerBuilder shareName(String name) {
      this.shareName = name;
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
    public CustomerBuilder quantity(float quant) {
      this.quantity = quant;
      return this;
    }
    public CustomerBuilder stockSymbol(String symbol) {
      this.stockSymbol = symbol;
      return this;
    }

    public StockImpl create() {
      return new StockImpl(shareName, purchaseDate, purchaseValue, quantity, stockSymbol);
    }
  }

  @Override
  public String getStockName() {
    return shareName;
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
    return purchaseValue;
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public float getValue(LocalDate d) {
    Connection c =  new ConnectionImpl();
    InputStream apiData = c.fetch(stockSymbol, d);
    StringBuilder output = new StringBuilder();
    try {
      int b;
      while ((b = apiData.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    String[] res = output.toString().split(",");
    return Float.parseFloat(res[10]);
  }

}
