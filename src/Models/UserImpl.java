package Models;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements User{

  List<Portfolio> portFolios;
  String userName;

  public UserImpl(String userName,List<Portfolio> portFolios)
  {
    this.userName = userName;
    this.portFolios = portFolios;
  }

  public UserImpl()
  {

  }

  @Override
  public Portfolio getPortfolio(String portFolioName) {
    return null;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public float computePortFolio() {
    return 0;
  }

  @Override
  public Stock removeStock(Portfolio n, String a) {
    return null;
  }

  @Override
  public void addPortfolio(Portfolio n) {

  }

  @Override
  public void addShare(Portfolio A, String k) {

  }

  @Override
  public float sellPortfolio(Portfolio o) {
    return 0;
  }

  @Override
  public List<Portfolio> getAllportfolios() {
    return portFolios;
  }

  @Override
  public Portfolio deletePortfolio(String name) {
    return null;
  }

  @Override
  public List<Portfolio> removeAllPortfolios() {
    return null;
  }

  public static userBuilder CreateBuilder()
  {
    return new userBuilder();
  }

  public static class userBuilder
  {
    String username;
    List<Portfolio> portfolioList;

    userBuilder()
    {
      username ="";
      portfolioList = new ArrayList<>();
    }

   public userBuilder setUserName(String username)
    {
      this.username = username;
      return this;
    }

    public userBuilder addAllPortfolioList(List<Portfolio> m)
    {
      this.portfolioList.addAll(m);
      return this;
    }

   public userBuilder addPortfolioList(Portfolio m)
    {
      this.portfolioList.add(m);
      return this;
    }

    public UserImpl create(){
      return new UserImpl(this.username,this.portfolioList);
    }

  }
}
