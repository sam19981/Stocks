import Controller.IController;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import Controller.TextController;
import Models.IModel;
import Models.Model;
import View.IView;
import View.TextView;

public class ProgramRunnerClass {

  public static void main(String[] args) {

    IModel model = new Model();
    IView view = new TextView(System.out);
    IController controller = new TextController(model,System.in,view);
    controller.go();

  }
}
