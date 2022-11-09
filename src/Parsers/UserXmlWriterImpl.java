package parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import model.Portfolio;
import model.Stock;
import model.User;

/**
 *  Class responsible for writing user object to the file in the xml format.
 */
public class UserXmlWriterImpl implements XmlWriter {

  @Override
  public int writeData(String file, User data) throws ParserConfigurationException {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    Document doc = docBuilder.newDocument();
    Element rootElement = doc.createElement("user");
    doc.appendChild(rootElement);

    Element name = doc.createElement("username");
    name.setTextContent(data.getUserName());
    rootElement.appendChild(name);
    Element name1;
    Element name2;
    Element name3;
    Element name4;
    Element name5;
    Element name6;
    List<Portfolio> portfolioList = data.getAllPortfolios();
    for (Portfolio temp : portfolioList) {
      name1 = doc.createElement("portfolio");
      name1.setAttribute("id", temp.getPortfolioName());
      List<Stock> temp1 = temp.getAllStocks();
      for (Stock stock : temp1) {
        name2 = doc.createElement("stock");
        name2.setAttribute("id", stock.getStockName());
        name3 = doc.createElement("cost");
        name3.setTextContent(String.valueOf(stock.getPurchaseValue()));
        name2.appendChild(name3);
        name4 = doc.createElement("quantity");
        name4.setTextContent(String.valueOf(stock.getQuantity()));
        name2.appendChild(name4);
        name5 = doc.createElement("time");
        name5.setTextContent(String.valueOf(stock.getPurchaseDate()).replace("-", "/"));
        name2.appendChild(name5);
        name6 = doc.createElement("symbol");
        name6.setTextContent(String.valueOf(stock.getStockSymbol()));
        name2.appendChild(name6);
        name1.appendChild(name2);
      }
      rootElement.appendChild(name1);
    }

    try (FileOutputStream output =
                 new FileOutputStream(file)) {
      writeXml(doc, output);
    } catch (IOException | TransformerException e) {
      e.printStackTrace();
    }
    return 0;
  }

  private static void writeXml(Document doc,
                               OutputStream output)
          throws TransformerException {

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();

    // pretty print
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(output);

    transformer.transform(source, result);

  }
}
