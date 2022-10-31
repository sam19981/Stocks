package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserImpl implements User{
  private Map<String, Portfolio> portFolios;
  private String userName;
  private String password;

  public UserImpl(String userName,List<Portfolio> ports, String pass)
  {
    this.userName = userName;
    this.portFolios = new LinkedHashMap<>();
    for(Portfolio portfolio: ports) {
      portFolios.putIfAbsent(portfolio.getPortfolioName(), portfolio);
    }
    this.password = pass;
  }

  public UserImpl() {
  }

  @Override
  public Portfolio getPortfolio(String portFolioName) {
    if(portFolios.get(portFolioName)!=null) {
      return portFolios.get(portFolioName);
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
    for(Portfolio portfolio: portFolios.values()) {
      total+= portfolio.getPortfolioValue(d);
    }
    return total;
  }

  @Override
  public float computePortfolioValue(String PortfolioName, LocalDate d) {
    if(d!=null) {
      if(portFolios.get(PortfolioName)!=null) {
        return portFolios.get(PortfolioName).getPortfolioValue(d);
      }
    }
    return -1;
  }

  @Override
  public Stock removeStock(Portfolio n, Stock a) {
    if(portFolios.get(n.getPortfolioName())!=null){
      for(Portfolio p: portFolios.values()) {
        if(p.equals(n)){
          p.sellStock(a);
        }
      }
    }
    return null;
  }

  @Override
  public int addPortfolio(Portfolio n) {
    if(portFolios.get(n.getPortfolioName())==null) {
      portFolios.put(n.getPortfolioName(), n);
      return 0;
    }
    else{
      return -1;
    }
  }

  @Override
  public void addStock(Portfolio A, Stock k) {
    for(Portfolio p: portFolios.values()) {
      if(p.equals(A)) {
        A.addStock(k);
      }
    }
  }

  @Override
  public float sellPortfolio(Portfolio o) {
    if(portFolios.get(o.getPortfolioName())!=null) {
      float res = o.getPortfolioValue(LocalDate.now());
      portFolios.remove(o.getPortfolioName());
      return res;
    }
    return -1;
  }

  @Override
  public List<Portfolio> getAllPortfolios() {
    return new ArrayList<>(portFolios.values());
  }

  @Override
  public Portfolio deletePortfolio(String name) {
    Portfolio r = PortfolioImpl.getBuilder().create();
    for(Portfolio p: portFolios.values()){
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
    String password;

    userBuilder()
    {
      username ="";
      portfolioList = new ArrayList<>();
      password = "";
    }


   public userBuilder setUserName(String username)
    {
      this.username = username;
      return this;
    }

    public userBuilder setPassword(String password)
    {
      this.password = password;
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
      if(this.username.equals("")) {
        return null;
      }
      return new UserImpl(this.username, this.portfolioList, this.password);
    }

  }

}
