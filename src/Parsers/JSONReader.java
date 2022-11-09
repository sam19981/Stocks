package parsers;

import java.io.File;

import model.User;
import model.UserImpl;

public class JSONReader implements XmlReader{

  @Override
  public User readData(String fileName, String password) {
    UserImpl.UserBuilder user = UserImpl.createBuilder();
    File f = new File(fileName);
    if (!f.exists()) {
      return null;
    }
    return user.create();
  }
}
