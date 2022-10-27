package Parsers;

import javax.xml.parsers.ParserConfigurationException;

import Models.User;

public interface xmlWriter {

 int writeData(String File, User user) throws ParserConfigurationException;

}
