package Connections;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class ConnectionImpl implements Connection
{
  URL url = null;
  @Override
  public InputStream fetch(String stockSymbol, LocalDate date) {
    try {
      LocalTime time = LocalTime.parse("14:00:00");
      ZoneOffset zone = ZoneOffset.of("Z");
      long t = time.toEpochSecond(date, zone);
      url = new URL("https://query1.finance.yahoo.com/v7/finance/download/"
              +stockSymbol+"?symbol="+stockSymbol+"&period1="+t+"&period2="+t+"&interval=1d");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the API has either changed or no longer works");
    }
    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try{
    return url.openStream();
    } catch (IOException e) {
      return null;
    }

  }



}
