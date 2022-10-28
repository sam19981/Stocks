package Parsers;

import Models.User;

public interface xmlReader  {

  User readData(String Filename, String password);
}
