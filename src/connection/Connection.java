package connection;

import java.io.InputStream;
import java.time.LocalDate;

/**
 * The Connection class defined below is used to fetch data from the Yahoo API.
 * This part is separated into a new Class as it would be easier to accommodate adding.
 * Alpha Vantage API or any other changes asked in the future assignments.
 */
public interface Connection {
  /**
   * The following is the method used to fetch the value of a given stock on a given date.
   * given following inputs.
   *
   * @param stockSymbol - The stock whose value is to be fetched.
   * @param d           - Date on which the value has to be computed.
   * @return - InputStream after the API call is made.
   */
  InputStream fetch(String stockSymbol, LocalDate d);
}
