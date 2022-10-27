package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements User{
  @Override
  public Stock removeShare(Portfolio n, String a) {
    return null;
  }

  private List<Portfolio> portFolios;
  private String userName;

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
    for(Portfolio portfolio: portFolios) {
      if(portfolio.getPortfolioName().equals(portFolioName)) {
        return portfolio;
      }
    }
    return null;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public float computeAllPortFolios(LocalDate d) {
    float total = 0;
    for(Portfolio portfolio: portFolios) {
      total+= portfolio.getPortfolioValue(d);
    }
    return total;
  }

  @Override
  public float computePortfolioValue(String PortfolioName, LocalDate d) {
    if(d!=null) {
      for (Portfolio portfolio : portFolios) {
        if (portfolio.getPortfolioName().equals(PortfolioName)) {
          return portfolio.getPortfolioValue(d);
        }
      }
    }
    return -1;
  }

  @Override
  public Stock removeStock(Portfolio n, Stock a) {
    if(portFolios.contains(n)){
      for(Portfolio p: portFolios) {
        if(p.equals(n)){
          p.sellStock(a);
        }
      }
    }
    return null;
  }

  @Override
  public void addPortfolio(Portfolio n) {
    portFolios.add(n);
  }

  @Override
  public void addStock(Portfolio A, Stock k) {
    for(Portfolio p: portFolios) {
      if(p.equals(A)) {
        A.addStock(k);
      }
    }
  }

  @Override
  public float sellPortfolio(Portfolio o) {
    if(portFolios.contains(o)) {
      float res = o.getPortfolioValue(LocalDate.now());
      portFolios.remove(o);
      return res;
    }
    return -1;
  }

  @Override
  public List<Portfolio> getAllPortfolios() {
    return portFolios;
  }

  @Override
  public Portfolio deletePortfolio(String name) {
    Portfolio r = PortfolioImpl.getBuilder().create();
    for(Portfolio p: portFolios){
      if(p.getPortfolioName().equals(name)){
        r = p;
        sellPortfolio(p);
        break;
      }
    }
    return r;
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
