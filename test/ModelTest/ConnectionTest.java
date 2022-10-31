package ModelTest;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import Connections.Connection;
import Connections.ConnectionImpl;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ConnectionTest {
  Connection connection;
  @Before
  public void setup() {
    connection = new ConnectionImpl();
  }

  @Test
  public void TestForValidConnection() {
    assertNotNull(connection.fetch("GOOG", LocalDate.of(2020,3,20)));
  }

  @Test
  public void TestForInvalidConnection() {
    assertNull(connection.fetch("GOO", LocalDate.of(2020,3,20)));
  }

  @Test
  public void TestMalformedURLConnection() {
    assertNull(connection.fetch("90()**+\\", LocalDate.of(2020,3,20)));
  }

  @Test
  public void TestFutureDate() {
    assertNull(connection.fetch("GOOG", LocalDate.of(2025,3,20)));
  }

  @Test
  public void TestToday() {
    assertNotNull(connection.fetch("GOOG", LocalDate.now()));
  }

}
