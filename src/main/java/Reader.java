import java.io.FileReader;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Reader<T> {

  public UserCollection read(T t, String classType) {
    ObjectMapper mapper = new ObjectMapper();
    UserCollection retT = null;
    try {
      // Writing to a file
      retT = mapper.readValue(new FileReader("output/Output.json"), UserCollection.class); //getClassType
    } catch (
            IOException e) {
      e.printStackTrace();
    }
    return retT;
  }
}
