import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import Controller.IController;
import Controller.TextController;
import Models.IModel;
import Models.Model;
import View.IView;
import View.TextView;

public class ProgramRunnerClass {

  public static void main(String[] args) {
    LocalTime time = LocalTime.now();
    ZoneOffset zone = ZoneOffset.of("Z");
    LocalDate date = LocalDate.of(2020, 2, 10);
    long t = time.toEpochSecond(date, zone);
    String stockSymbol = "GOOG";
    try{
    URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/"
            +stockSymbol+"?symbol="+stockSymbol+"&period1="+t+"&period2="+t+"&interval=1d");
      System.out.println(url.openStream());
  } catch(MalformedURLException e) {
    throw new RuntimeException("the API has either changed or no longer works");
  } catch (IOException e) {
      throw new RuntimeException(e);
    }


    IModel model = new Model();
    IView view = new TextView(System.out);
    IController controller = new TextController(model,System.in,view);
    controller.go();
  }
}
