package Models;

import java.time.LocalDate;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public interface IModel {
  int createUser(String username, String password);
  int setUser(String username, String password);
  List<Portfolio> getUserPortFolios();
  float computePortfolioValues(String PortfolioName, LocalDate d);
  LocalDate createValidDate(String year, String month, String date);
  int checkValidNumber(String number);
  void addPortfolios(String PortfolioName);
  Stock createStock(String sName, String quantity, String date, String month, String value,
                    String symbol);
  void addStock(Portfolio p, Stock A) ;
  int validateStocksymbol(String symbol);
}
