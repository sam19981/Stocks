public interface User {
  void addPortfolio(Portfolio p);

  void pickShares(String[] shares);

  Portfolio[] viewPortfolios();

  Portfolio deletePortfolio(String PortfolioName);

  float computePortfolioValue(String PortfolioName);

  //TBC
  //void addUserDetails();


}
