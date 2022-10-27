import Controller.IController;
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
