package Models;

import java.time.LocalDate;

public interface Stock {

  String getStockName();

  float getQuantity();

  LocalDate getPurchaseDate();

  float getPurchaseValue();

  float getValue(LocalDate d);

  String getStockSymbol();
  StockImpl increaseQuantity(float i);
}
