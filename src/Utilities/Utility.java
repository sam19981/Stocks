package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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


  public static StringBuilder readJSONData(InputStream in) throws IOException {
    StringBuilder output = new StringBuilder();
    int b;
    while ((b=in.read())!=-1) {
      output.append((char)b);
    }
    return output;
  }

  public static float readOnDate(StringBuilder output, LocalDate date) {
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    String searchString = date.format(formatters);
    float value = 0;
    boolean flag = false;
    String lastDate = "";
    String[] dates = String.valueOf(output).split("\n");
    for(int ix =dates.length-1; ix>=0; ix--) {
      if(dates[ix].contains("-")) {
        lastDate = dates[ix];
        break;
      }
    }
    lastDate = lastDate.split(":")[0].replaceAll("\"", "").strip();
    LocalDate lastKnownDate = LocalDate.parse(lastDate, formatters);

    if(lastDate.isEmpty() || date.isBefore(lastKnownDate)){
      return 0;
    }
    for(String keyValue : dates) {
      if(keyValue.contains(searchString)){
        flag = true;
      }
      if(flag & keyValue.contains("close")){
        String[] s= keyValue.split(": ");
        String st = s[1].split(",")[0].replaceAll("\"","");
        value = Float.parseFloat(st);
        break;
      }
    }

    if(!flag) {
      value = readOnDate(output, date.minusDays(1));
    }
    return value;
  }

}
