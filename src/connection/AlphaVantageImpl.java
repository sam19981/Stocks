package connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class AlphaVantageImpl implements Connection{
  URL url;
  String apiKey = "QC6PHJMTIZHC8S6B";

  @Override
  public InputStream fetch(String stockSymbol, LocalDate date) {
    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey="+apiKey+"&datatype=json");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      return url.openStream();
    } catch (IOException e) {
      return null;
    }
  }
}
