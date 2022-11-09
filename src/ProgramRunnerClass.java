import java.io.IOException;

import controller.IController;
import controller.TextController;
import model.IModel;
import model.Model;
import view.IView;
import view.TextView;

/**
 * The following class hosts the main method.
 * It is the one that will be called when running the jar.
 */
public class ProgramRunnerClass {

  /**
   * The below main method instantiates objects of the Model, View and the Controller.
   * It also sets the program in motion by the go function of the Controller.
   *
   * @param args - input arguments if any.
   */
  public static void main(String[] args) throws IOException {
    IModel model = new Model();
    IView view = new TextView(System.out);
    IController controller = new TextController(model, System.in, view);
    controller.connect();
  }
}
