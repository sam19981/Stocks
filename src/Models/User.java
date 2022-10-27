package Models;

import java.time.LocalDate;
import java.util.List;

public interface User {

  byte[] encryptPass(String password);

  String decryptPass(byte[] encrypted);

  byte[] encryptedPass();

  Portfolio getPortfolio(String name);

  String getUserName();

  float computeAllPortFolios(LocalDate d);

  float computePortfolioValue(String PortfolioNam, LocalDate d);

  Stock removeStock(Portfolio n, Stock a);

  void addPortfolio(Portfolio n);

  void addStock(Portfolio A,Stock s);

  float sellPortfolio(Portfolio o);

  List<Portfolio> getAllPortfolios();

  Portfolio deletePortfolio(String name);

  List<Portfolio> removeAllPortfolios();


}
