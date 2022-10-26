package Models;

import java.util.List;

public interface User {

  Portfolio getPortfolio(String name);

  String getUserName();

  float computePortFolio();

  Stock removeStock(Portfolio n, String a);
  Stock removeShare(Portfolio n, String a);

  void addPortfolio(Portfolio n);

  void addShare(Portfolio A,String k);

  float sellPortfolio(Portfolio o);

  List<Portfolio> getAllportfolios();

  Portfolio deletePortfolio(String name);

  List<Portfolio> removeAllPortfolios();
}
