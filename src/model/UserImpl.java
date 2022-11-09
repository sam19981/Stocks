package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to store all the data related to the user who is currently logged in
 * and is using the program.
 */
public class UserImpl implements User {
  private Map<String, Portfolio> portFolios;
  private String userName;

  /**
   *  Constructor to initialize all the data related to a user in the memory.
   * @param userName - userName of the user to be initialized.
   * @param ports - portfolios of the user to be used.
   */
  public UserImpl(String userName, List<Portfolio> ports) {
    this.userName = userName;
    this.portFolios = new LinkedHashMap<>();
    for (Portfolio portfolio : ports) {
      portFolios.putIfAbsent(portfolio.getPortfolioName(), portfolio);
    }
  }

  @Override
  public Portfolio getPortfolio(String portFolioName) {
    if (portFolios.get(portFolioName) != null) {
      return portFolios.get(portFolioName);
    }
    return null;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public float computeAllPortFolios(LocalDate date) {
    float total = 0;
    for (Portfolio portfolio : portFolios.values()) {
      total += portfolio.getPortfolioValue(date);
    }
    return total;
  }

  @Override
  public float computePortfolioValue(String portfolioName, LocalDate date) {
    if (date != null) {
      if (portFolios.get(portfolioName) != null) {
        return portFolios.get(portfolioName).getPortfolioValue(date);
      }
    }
    return -1;
  }

  @Override
  public Stock removeStock(Portfolio newPort, Stock stock) {
    if (portFolios.get(newPort.getPortfolioName()) != null) {
      for (Portfolio p : portFolios.values()) {
        if (p.equals(newPort)) {
          p.sellStock(stock);
        }
      }
    }
    return null;
  }

  @Override
  public int addPortfolio(Portfolio newPort) {
    if (portFolios.get(newPort.getPortfolioName()) == null) {
      portFolios.put(newPort.getPortfolioName(), newPort);
      return 0;
    } else {
      return -1;
    }
  }

  @Override
  public void addStock(Portfolio port, Stock stock) {
    for (Portfolio p : portFolios.values()) {
      if (p.equals(port)) {
        port.addStock(stock);
      }
    }
  }

  @Override
  public float sellPortfolio(Portfolio port) {
    if (portFolios.get(port.getPortfolioName()) != null) {
      float res = port.getPortfolioValue(LocalDate.now());
      portFolios.remove(port.getPortfolioName());
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
    for (Portfolio p : portFolios.values()) {
      if (p.getPortfolioName().equals(name)) {
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

  public static UserBuilder createBuilder() {
    return new UserBuilder();
  }

  /**
   * Builder to set all the fields in the user without any errors.
   */
  public static class UserBuilder {
    String username;
    List<Portfolio> portfolioList;
    String password;

    UserBuilder() {
      username = "";
      portfolioList = new ArrayList<>();
      password = "";
    }


    public UserBuilder setUserName(String username) {
      this.username = username;
      return this;
    }

    public UserBuilder setPassword(String password) {
      this.password = password;
      return this;
    }

    public UserBuilder addAllPortfolioList(List<Portfolio> m) {
      this.portfolioList.addAll(m);
      return this;
    }

    public UserBuilder addPortfolioList(Portfolio m) {
      this.portfolioList.add(m);
      return this;
    }

    /**
     * Creates the userimpl object after setting
     * all the values using the builder.
     * @return - new userImpl object.
     */
    public UserImpl create() {
      if (this.username.equals("") || this.password.equals("")) {
        return null;
      }
      return new UserImpl(this.username, this.portfolioList);
    }

  }

}
