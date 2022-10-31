package Models;

import java.time.LocalDate;
import java.util.ArrayList;

public interface Portfolio {

  String getPortfolioName();

  ArrayList<Stock> getAllStocks();

  float getPortfolioValue(LocalDate d);

  void addStock(Stock A);

  float sellStock(Stock A);

  float sellAllStocks();

  Stock getStock(String A);


}
