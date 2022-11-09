package modeltest;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Portfolio;
import model.PortfolioImpl;
import model.Stock;
import model.StockImpl;

import static org.junit.Assert.assertEquals;

/**
 * class to check if the portfolio objects are getting set properly using a
 * builder and are behaving as expected.
 */
public class PortfolioTest {

  @Test
  public void testPortfolio() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create()).create();

    assertEquals("School Funds", builder.getPortfolioName());
    assertEquals("Google", builder.getStock("Google").getStockName());
    assertEquals("Goog", builder.getStock("Google").getStockSymbol());
    assertEquals(10, builder.getStock("Google").getQuantity(), 0);
    assertEquals(LocalDate.of(2020, 11, 2),
            builder.getStock("Google").getPurchaseDate());
    assertEquals((float) 81.3015, builder.getStock("Google").getPurchaseValue(), 0);

  }

  @Test
  public void testPortfoliowithTwostocks() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("College Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();

    assertEquals("College Funds", builder.getPortfolioName());
    assertEquals("Google", builder.getStock("Google").getStockName());
    assertEquals("Goog", builder.getStock("Google").getStockSymbol());
    assertEquals(10, builder.getStock("Google").getQuantity(), 0);
    assertEquals(LocalDate.of(2020, 11, 2),
            builder.getStock("Google").getPurchaseDate());
    assertEquals((float) 81.3015, builder.getStock("Google").getPurchaseValue(), 0);

    assertEquals("College Funds", builder.getPortfolioName());
    assertEquals("Apple", builder.getStock("Apple").getStockName());
    assertEquals("AAPL", builder.getStock("Apple").getStockSymbol());
    assertEquals(14, builder.getStock("Apple").getQuantity(), 0);
    assertEquals(LocalDate.of(2020, 5, 1),
            builder.getStock("Apple").getPurchaseDate());
    assertEquals((float) 81.3015, builder.getStock("Google").getPurchaseValue(), 0);

  }

  @Test
  public void testPortfoliowithTwoSamestocksOnePortfolio() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 10, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();

    assertEquals("School Funds", builder.getPortfolioName());
    assertEquals("Google", builder.getStock("Google").getStockName());
    assertEquals("Goog", builder.getStock("Google").getStockSymbol());
    assertEquals(20, builder.getStock("Google").getQuantity(), 0);
    assertEquals(LocalDate.of(2020, 11, 2),
            builder.getStock("Google").getPurchaseDate());
    assertEquals((float) 81.3015, builder.getStock("Google").getPurchaseValue(), 0);

    assertEquals("School Funds", builder.getPortfolioName());
    assertEquals("Apple", builder.getStock("Apple").getStockName());
    assertEquals("AAPL", builder.getStock("Apple").getStockSymbol());
    assertEquals(14, builder.getStock("Apple").getQuantity(), 0);
    assertEquals(LocalDate.of(2020, 5, 1),
            builder.getStock("Apple").getPurchaseDate());
    assertEquals((float) 81.3015, builder.getStock("Google").getPurchaseValue(), 0);

  }

  @Test
  public void testAddstocks() {
    List<Stock> stockList = new ArrayList<>();
    stockList.add(StockImpl.getBuilder()
            .stockName("Google")
            .stockSymbol("Goog")
            .quantity(10)
            .purchaseDate(LocalDate.of(2020, 11, 2))
            .purchaseValue((float) 81.3015)
            .create());
    stockList.add(StockImpl.getBuilder()
            .stockName("Apple")
            .stockSymbol("AAPL")
            .quantity(14)
            .purchaseDate(LocalDate.of(2020, 5, 1))
            .create());

    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds").stocks(stockList).create();

    assertEquals(stockList.get(0), builder.getStock("Google"));
    assertEquals(stockList.get(1), builder.getStock("Apple"));
  }

  @Test
  public void computePortfolio() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 10, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();
    assertEquals(4151.7001953125,
            builder.getPortfolioValue(LocalDate.of(2022, 10, 24)), 0);
  }


  @Test
  public void computePortfolioFutureDate() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 10, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();
    assertEquals(0,
            builder.getPortfolioValue(LocalDate.of(2023, 10, 24)), 0);
  }

  @Test
  public void computePortfolioSunday() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 10, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();
    assertEquals(builder.getPortfolioValue(LocalDate.of(2022, 10, 28)),
            builder.getPortfolioValue(LocalDate.of(2022, 10, 30)), 0);
  }

  @Test
  public void computePortfolioInvalidDate() {
    Portfolio builder =
            PortfolioImpl.getBuilder().portfolioName("School Funds")
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 11, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Google")
                            .stockSymbol("Goog")
                            .quantity(10)
                            .purchaseDate(LocalDate.of(2020, 10, 2))
                            .purchaseValue((float) 81.3015)
                            .create())
                    .addStocks(StockImpl.getBuilder()
                            .stockName("Apple")
                            .stockSymbol("AAPL")
                            .quantity(14)
                            .purchaseDate(LocalDate.of(2020, 5, 1))
                            .create())
                    .create();
    assertEquals(0,
            builder.getPortfolioValue(LocalDate.of(20, 10, 30)), 0);
  }

}
