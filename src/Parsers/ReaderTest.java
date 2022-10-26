package Parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Models.User;
import Models.UserImpl;

public class ReaderTest implements xmlReader{

  final String user= "user";
  final String portfolio = "portfolio";
  @Override
  public User readData(String Filename) {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


    try {

      // optional, but recommended
      // process XML securely, avoid attacks like XML External Entities (XXE)
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(Filename));

      // optional, but recommended
      // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
      doc.getDocumentElement().normalize();
      System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
      System.out.println("------");
      NodeList list = doc.getElementsByTagName(portfolio);

      for (int temp = 0; temp < list.getLength(); temp++) {
        Node node = list.item(temp);

      }

    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (SAXException e) {
      throw new RuntimeException(e);
    }
    return new UserImpl();
  }
  }
