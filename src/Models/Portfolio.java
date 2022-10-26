package Models;

import java.time.LocalDate;
import java.util.List;

public interface Portfolio {

  String getPortfolioName();

  List<Stock> getAllStocks();
  List<Stock> getAllStock();

  float getPortfolioValue(LocalDate d);

  void addStock(Stock A);

  float sellStock(Stock A);

  float sellAllStocks();

  Stock getStock(String A);


}
