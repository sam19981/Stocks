package Parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import Models.PortfolioImpl;
import Models.StockImpl;
import Models.User;
import Models.UserImpl;

public class UserXmlReaderImpl implements xmlReader{

  final String userName= "username";
  final String portfolio = "portfolio";
  @Override
  public User readData(String Filename, String password) {
    UserImpl.userBuilder user = UserImpl.CreateBuilder();

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    HashSet<String> hashMap = new HashSet<String>();


    try {

      // optional, but recommended
      // process XML securely, avoid attacks like XML External Entities (XXE)
      dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      // parse XML file
      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.parse(new File(Filename));

      StockImpl.CustomerBuilder stock1;
      PortfolioImpl.CustomerBuilder newPortfolio;
      // optional, but recommended
      // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
      doc.getDocumentElement().normalize();
//      System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
//      System.out.println("------");
      NodeList root = doc.getChildNodes();

      String expression3 = "/user/username";
      XPath xPath3 =  XPathFactory.newInstance().newXPath();
      NodeList nodeList3 = (NodeList) xPath3.compile(expression3).evaluate(
              doc, XPathConstants.NODESET);
      Node n = nodeList3.item(0);
      user.setUserName(n.getTextContent());
      user.setPassword(password);
      String expression = "/user/portfolio";
      XPath xPath =  XPathFactory.newInstance().newXPath();
      NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
              doc, XPathConstants.NODESET);
      for (int i = 0; i < nodeList.getLength(); i++) {
        newPortfolio= PortfolioImpl.getBuilder();
        Node nNode = nodeList.item(i);
//        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;
//          System.out.println("Portfolio Name :" + eElement.getAttribute("id"));
          String idName = eElement.getAttribute("id");
          newPortfolio.portfolioName(idName);
          /////////////////////////////////////////////////////////////////////////////
          String expression1 = "/user/portfolio"+"[@id='"+idName+"']/stock";
//          System.out.println(expression1);
          XPath xPath1 =  XPathFactory.newInstance().newXPath();
          NodeList nodeList1 = (NodeList) xPath1.compile(expression1).evaluate(
                  doc, XPathConstants.NODESET);
          stock1 = StockImpl.getBuilder();
          for (int j = 0; j < nodeList1.getLength(); j++)
          {
            Node nNode1 = nodeList1.item(j);
//            System.out.println("\nCurrent Element :" + nNode1.getAttributes());
            if (nNode1.getNodeType() == Node.ELEMENT_NODE)
            {Element eElement1 = (Element) nNode1;
//              System.out.println("Stock Name :" + eElement1.getAttribute("id"));
              stock1.shareName(eElement1.getAttribute("id"));
              NodeList a = eElement1.getChildNodes();
              for (int k = 0; k < a.getLength(); k++)
              {
                Node currentode1 = a.item(k);
                if(currentode1.getNodeName().equals("cost"))
                {stock1.purchaseValue(Float.parseFloat(currentode1.getTextContent()));}
                else if(currentode1.getNodeName().equals("quantity"))
                {stock1.quantity(Float.parseFloat(currentode1.getTextContent()));}
                else if(currentode1.getNodeName().equals("time")) {
                  String date = currentode1.getTextContent();
                  String[] yymmdd= date.split("/");
                  stock1.purchaseDate(LocalDate.of(Integer.parseInt(yymmdd[0]),
                          Integer.parseInt(yymmdd[1]),Integer.parseInt(yymmdd[1])));
                }
                else if(currentode1.getNodeName().equals("symbol"))
                {
                  stock1.stockSymbol(currentode1.getTextContent());
                }
                }
              newPortfolio.addStocks(stock1.create());
            }
          }

        }
        if(!hashMap.contains(newPortfolio.getportfolioName()))
        {user.addPortfolioList(newPortfolio.create());
        hashMap.add(newPortfolio.getportfolioName());
        }


      }


    } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
      throw new RuntimeException(e);
    }
    return user.create();
  }
  }
