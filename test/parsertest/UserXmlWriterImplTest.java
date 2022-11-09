package parsertest;

import org.junit.Test;

import java.time.LocalDate;

import javax.xml.parsers.ParserConfigurationException;

import model.PortfolioImpl;
import model.StockImpl;
import model.User;
import model.UserImpl;
import parsers.UserXmlReaderImpl;
import parsers.UserXmlWriterImpl;
import parsers.XmlReader;
import parsers.XmlWriter;

import static org.junit.Assert.assertEquals;

/**
 * Class to check the xmlWriter by writing the given user object to a file
 * and then reading it from the file and comparing if the user objects
 * are same.
 */
public class UserXmlWriterImplTest {

  @Test
  public void testWriter() throws ParserConfigurationException {
    XmlWriter newWriter = new UserXmlWriterImpl();
    XmlReader newReader = new UserXmlReaderImpl();
    User checkUser =
            UserImpl.createBuilder().setUserName("emand").
                    addPortfolioList(PortfolioImpl.getBuilder().
                            portfolioName("Port1").
                            addStocks(StockImpl.getBuilder()
                                    .stockName("Google").
                                    purchaseValue(53.616f)
                                    .quantity(100.0f).stockSymbol("GOOG").
                                    purchaseDate(LocalDate.of(2020, 3, 20))
                                    .create())
                            .create()).
                    create();
    newWriter.writeData("users/writeTestFile.txt", checkUser);

    User newUser = newReader.readData("users/writeTestFile.txt", "");
    assertEquals(checkUser.getUserName(), newUser.getUserName());
    assertEquals(checkUser.getUserName(), newUser.getUserName());
    assertEquals(checkUser.getPortfolio("Port1").getPortfolioName(),
            newUser.getPortfolio("Port1").getPortfolioName());
    assertEquals(checkUser.getPortfolio("Port1").getStock("Google").getStockName(),
            newUser.getPortfolio("Port1").getStock("Google").getStockName());
    assertEquals(checkUser.getPortfolio("Port1").getStock("Google").getQuantity(),
            newUser.getPortfolio("Port1").getStock("Google").getQuantity(), 0);
  }

}