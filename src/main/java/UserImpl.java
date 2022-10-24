import java.util.HashMap;

public class UserImpl implements User{
  private String userName;
  private HashMap<String, Portfolio> portfolios;

  @Override
  public void addPortfolio(Portfolio p) {

  }

  @Override
  public void pickShares(String[] shares) {

  }

  @Override
  public Portfolio[] viewPortfolios() {
    return new Portfolio[0];
  }

  @Override
  public Portfolio deletePortfolio(String PortfolioName) {
    return null;
  }

  @Override
  public float computePortfolioValue(String PortfolioName) {
    return 0;
  }

  //ComputePortFolio

  //public Portfolio
}
