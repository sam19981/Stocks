import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PortfolioImpl implements Portfolio{
  private final HashMap<String, Share> shares;
  private final String portfolioName;

  PortfolioImpl(){
    shares = new HashMap<>();
    portfolioName = "";
  }


  @Override
  public void addShare(Share s) {

  }

  @Override
  public Share deleteShare(String shareName) {
    return null;
  }
}
