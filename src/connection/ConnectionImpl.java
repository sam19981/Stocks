package connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * The following is an implementation of the Connection class which makes use of Yahoo API.
 * to fetch the stock value.
 * More implementations like this could be added.
 * in the future if the support is extended for more APIs.
 */
public class ConnectionImpl implements Connection {
  URL url = null;

  @Override
  public InputStream fetch(String stockSymbol, LocalDate date) {
    try {
      LocalTime time = LocalTime.of(13, 50);
      ZoneOffset zone = ZoneOffset.of("-04:00");
      if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
        date = date.minusDays(1);
      } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
        date = date.minusDays(2);
      }
      long t = time.toEpochSecond(date, zone);
      url = new URL("https://query1.finance.yahoo.com/v7/finance/download/"
              + stockSymbol + "?symbol=" + stockSymbol + "&period1=" + t
              + "&period2=" + t + "&interval=1d");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the API has either changed or no longer works");
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