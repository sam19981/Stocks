package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the utility functions which can be reused throughout the program.
 */
public class Utility {

  /**
   * Reads the CSV data from the file returns it for further use.
   * @param fileName - The filename from which the data has to be read.
   * @return - returns the data as a list of strings.
   * @throws IOException - The exception is thrown when the
   *                       file is ot present or cannot be read.
   */
  public static List<String> loadCsvData(String fileName) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    List<String> values = new ArrayList<>();
    String line;
    while ((line = br.readLine()) != null) {
      values.add(line);
    }
    return values;
  }

}
