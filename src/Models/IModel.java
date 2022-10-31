package Models;

import java.time.LocalDate;
import java.util.List;

public interface IModel {
  int directLoadData(String username, String password);

  boolean checkExistingUser(String username);

  int createUser(String username, String password, String fileName);

  int setUser(String username, String password);
  List<Portfolio> getUserPortFolios();
  float computePortfolioValues(String PortfolioName, LocalDate d);
  LocalDate createValidDate(String year, String month, String date);
  float checkValidNumber(String number);
  int addPortfolios(String PortfolioName);
  Stock createStock(String sName, String quantity, String date, String month, String value,
                    String symbol);
  void addStock(Portfolio p, Stock A);
  int checkValidFile(String filename);
  boolean checkValidUserName(String username);
	int validateStocksymbol(String symbol);
  boolean checkValidPassword(String password);
}
