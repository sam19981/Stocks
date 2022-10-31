package Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utility {

  public static boolean checkValidDate(Integer year, Integer month, Integer day) {
    if(year<1970 || year>2022) {
      return false;
    }
    if(month<1 || month>12) {
      return false;
    }
    if(day<1 || day>31) {
      return true;
    }
    return true;
  }

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
