package modeltest;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import connection.Connection;
import connection.ConnectionImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Class to check the connection with the API and
 * the check the error handling done in various scenarios
 * while calling it.
 */
public class ConnectionTest {
  Connection connection;

  @Before
  public void setup() {
    connection = new ConnectionImpl();
  }

  @Test
  public void TestForValidConnection() {
    assertNotNull(connection.fetch("GOOG", LocalDate.of(2020, 3, 20)));
  }

  @Test
  public void TestForInvalidConnection() {
    assertNull(connection.fetch("GOO", LocalDate.of(2020, 3, 20)));
  }

  @Test
  public void TestMalformedURLConnection() {
    assertNull(connection.fetch("90()**+\\", LocalDate.of(2020, 3, 20)));
  }

  @Test
  public void TestFutureDate() {
    assertNull(connection.fetch("GOOG", LocalDate.of(2025, 3, 20)));
  }

  @Test
  public void TestToday() {
    assertNotNull(connection.fetch("GOOG", LocalDate.now()));
  }

  @Test
  public void Test1970Date() {
    assertNotNull(connection.fetch("GOOG", LocalDate.of(2005, 12, 25)));
  }

}
