package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UserImpl implements User{
  private List<Portfolio> portFolios;
  private String userName;
  private String password;

  public UserImpl(String userName,List<Portfolio> portFolios, String pass)
  {
    this.userName = userName;
    this.portFolios = portFolios;
    this.password = pass;
  }

  @Override
  public byte[] encryptPass(String password) {
    byte[] encrypted = new byte[1000];
    try
    {
      String key = "Bar12345Bar12345"; // 128-bit key
      // Create key and cipher
      Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      // encrypt the text
      cipher.init(Cipher.ENCRYPT_MODE, aesKey);
      encrypted = cipher.doFinal(password.getBytes());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    return encrypted;
  }

  @Override
  public String decryptPass(byte[] encrypted) {
    String decrypted = "";
    try {
      String key = "Bar12345Bar12345"; // 128-bit key
      // Create key and cipher
      Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, aesKey);
      decrypted = new String(cipher.doFinal(encrypted));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return decrypted;
  }

  @Override
  public String encryptedPass() {
    return password;
  }

  public UserImpl() {
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
