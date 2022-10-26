package Models;

import java.util.Date;

public interface Share {

  String getStockName();

  float getQuantity();

  Date getPurchaseDate();

  float getPurchaseValue();
}
