package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PortfolioImpl implements Portfolio{
  private final String portfolioName;
  private final Map<String, Stock> stocks;
  private PortfolioImpl(String name, List<Stock> s) {
    portfolioName = name;
    stocks = new HashMap<>();
    for(Stock stock: s) {
      if(stocks.get(stock.getStockName())!=null) {
        stocks.replace(stocks.get(stock.getStockName()).getStockName(),
                stocks.get(stock.getStockName()),
                stocks.get(stock.getStockName()).increaseQuantity(stock.getQuantity()));
      }
      else {
        stocks.put(stock.getStockName(), stock);
      }
    }
  }

  public static CustomerBuilder getBuilder() {
    return new CustomerBuilder();
  }

  public static class CustomerBuilder {
    private String portfolioName;
    private List<Stock> stocks;

    public String getportfolioName()
    {
      return this.portfolioName;
    }

    private CustomerBuilder() {
      portfolioName = "";
      stocks = new ArrayList<>();
    }

    public CustomerBuilder portfolioName(String name) {
      this.portfolioName = name;
      return this;
    }

    public CustomerBuilder stocks(List<Stock> s) {
      this.stocks.addAll(s);
      return this;
    }

    public CustomerBuilder addStocks(Stock s)
    {
      this.stocks.add(s);
      return this;
    }

    public PortfolioImpl create() {
      return new PortfolioImpl(portfolioName, stocks);
    }
  }

  @Override
  public String getPortfolioName() {
    return portfolioName;
  }

  @Override
  public ArrayList<Stock> getAllStocks() {
    return new ArrayList<>(stocks.values());
  }

  @Override
  public float getPortfolioValue(LocalDate d) {
    float total = 0;
    for (Stock stock : stocks.values()) {
      total += stock.getValue(d)*stock.getQuantity();
    }
    return total;
  }

  @Override
  public void addStock(Stock newStock) {
    if(stocks.get(newStock.getStockName())!=null) {
      stocks.replace(stocks.get(newStock.getStockName()).getStockName(),
              stocks.get(newStock.getStockName()),
              stocks.get(newStock.getStockName()).increaseQuantity(newStock.getQuantity()));
    }
    else {
      stocks.put(newStock.getStockName(), newStock);
    }
  }

  @Override
  public float sellStock(Stock A) {
    if(stocks.get(A.getStockName())!=null) {
      float res = A.getValue(LocalDate.now());
      stocks.remove(A.getStockName());
      return res;
    }
    return -1;
  }

  @Override
  public float sellAllStocks() {
    float res = getPortfolioValue(LocalDate.now());
    stocks.clear();
    return res;
  }

  @Override
  public Stock getStock(String A) {
    for(Stock stock: stocks.values()){
      if(stock.getStockName().equals(A)){
        return stock;
      }
    }
    return null;
  }
}
