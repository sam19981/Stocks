package Controller;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import Models.IModel;
import Models.Model;
import View.IView;
import View.TextView;

import static org.junit.Assert.*;


public class TextControllerTest {

   @Test
   public void TestGo() throws IOException {
     IModel mockModel = new Model();
     StringBuffer out = new StringBuffer();
     Reader in = new StringReader("E\nSam19981\nsam123\nS");
     IView view = new TextView(System.out);
     TextController contol = new TextController(mockModel,in,view);
     contol.go();
     System.out.println(out);
   }
}