package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioImpl implements Portfolio{
  private final String portfolioName;
  private final List<Stock> stocks;
  private PortfolioImpl(String name, List<Stock> s) {
    portfolioName = name;
    stocks = s;
  }

  public static CustomerBuilder getBuilder() {
    return new CustomerBuilder();
  }

  public static class CustomerBuilder {
    private String portfolioName;
    private List<Stock> stocks;

    private CustomerBuilder() {
      portfolioName = "";
      stocks = new ArrayList<>();
    }

    public CustomerBuilder portfolioName(String name) {
      this.portfolioName = name;
      return this;
    }

    public CustomerBuilder stocks(List<Stock> s) {
      this.stocks = s;
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
  public List<Stock> getAllStocks() {
    return stocks;
  }

  @Override
  public List<Stock> getAllStock() {
    return null;
  }

  @Override
  public float getPortfolioValue(LocalDate d) {
    float total = 0;
    for (Stock stock : stocks) {
      total += stock.getValue(d);
    }
    return total;
  }

  @Override
  public void addStock(Stock A) {
    stocks.add(A);
  }

  @Override
  public float sellStock(Stock A) {
    if(stocks.contains(A)) {
      float res = A.getValue(LocalDate.now());
      stocks.remove(A);
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
    for(Stock stock: stocks){
      if(stock.getStockName().equals(A)){
        return stock;
      }
    }
    return null;
  }
}
