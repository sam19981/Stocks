package Models;

import java.time.LocalDate;
import java.util.List;

public interface IModel {
  int createUser(String username);
  int setUser(String username);
  List<Portfolio> getUserPortFolios();
  float computePortfolioValues(String PortfolioName, LocalDate d);
  LocalDate createValidDate(String year, String month, String date);
  int checkValidNumber(String number);
}
