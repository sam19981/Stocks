package Models;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import Connections.Connection;
import Connections.ConnectionImpl;

public class StockImpl implements Stock {
  private final String stockName;
  private final LocalDate purchaseDate;
  private final float purchaseValue;
  private final float quantity;
  private final String stockSymbol;

  private StockImpl(String name, LocalDate date, float q,
                    String symbol, float pValue) {
    stockName = name;
    purchaseDate = date;
    quantity = q;
    stockSymbol = symbol;
    purchaseValue = pValue;
  }

  public static CustomerBuilder getBuilder() {
    return new CustomerBuilder();
  }

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

    public StockImpl create() {
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
    if(!stockSymbol.isEmpty()) {
      return getValue(purchaseDate);
    }
    else{
      return purchaseValue;
    }
  }

  @Override
  public String getStockSymbol() {
    return stockSymbol;
  }

  @Override
  public StockImpl increaseQuantity(float i) {
    return StockImpl.getBuilder().stockName(stockName)
            .quantity(quantity+i).purchaseDate(purchaseDate).stockSymbol(stockSymbol).create();
  }

  @Override
  public float getValue(LocalDate d) {
    Connection c =  new ConnectionImpl();
    StringBuilder output = new StringBuilder();
    try {
      InputStream apiData = c.fetch(stockSymbol, d);
      if(apiData==null) {
        throw new IllegalArgumentException("Invalid stock symbol provided");
      }
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
