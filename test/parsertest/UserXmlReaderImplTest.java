package parsertest;

import org.junit.Test;

import java.time.LocalDate;

import model.PortfolioImpl;
import model.StockImpl;
import model.User;
import model.UserImpl;
import parsers.UserXmlReaderImpl;
import parsers.XmlReader;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * class to check xmlreader class by reading the data in the file
 * and checking if the user object is loaded with the values as
 * expected.
 */
public class UserXmlReaderImplTest {

  @Test
  public void readerTest() {
    XmlReader newReader = new UserXmlReaderImpl();
    User newUser = newReader.readData("users/emandFile.txt", "");
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
    assertEquals(checkUser.getUserName(), newUser.getUserName());
    assertEquals(checkUser.getUserName(), newUser.getUserName());
    assertEquals(checkUser.getPortfolio("Port1").getPortfolioName(),
            newUser.getPortfolio("Port1").getPortfolioName());
    assertEquals(checkUser.getPortfolio("Port1").getStock("Google").getStockName(),
            newUser.getPortfolio("Port1").getStock("Google").getStockName());
    assertEquals(checkUser.getPortfolio("Port1").getStock("Google").getQuantity(),
            newUser.getPortfolio("Port1").getStock("Google").getQuantity(), 0);
  }

  @Test
  public void readerTestExceptionForWrongFileFormat() {
    XmlReader newReader = new UserXmlReaderImpl();
    User newUser = newReader.readData("users/EmanFile.txt", "");
    assertNull(newUser);
  }


}