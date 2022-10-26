package Models;

import java.util.List;

public interface Portfolio {

  String getPortfolioName();

  List<Share> getAllShares();

  float getPortfolioValue();

  void addShare(Share A);

  float sellShare(Share A);

  float sellAllShares();

  Share getShare(String A);


}
