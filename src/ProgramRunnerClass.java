import java.io.IOException;
import java.io.InputStreamReader;

import Controller.IController;

import Controller.TextController;
import Models.IModel;
import Models.Model;
import Models.User;
import Parsers.UserXmlReaderImpl;
import View.IView;
import View.TextView;

public class ProgramRunnerClass {

  public static void main(String[] args) throws IOException {
    IModel model = new Model();
    IView view = new TextView(System.out);
    IController controller = new TextController(model,new InputStreamReader(System.in),view);
    controller.go();
  }
}
