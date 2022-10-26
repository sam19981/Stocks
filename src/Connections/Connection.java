package Connections;

import java.io.InputStream;
import java.time.LocalDate;

public interface Connection {
  InputStream fetch(String stockSymbol, LocalDate d);
}
