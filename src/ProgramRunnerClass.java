import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import Models.Portfolio;
import Models.PortfolioImpl;
import Models.Stock;
import Models.StockImpl;
import Parsers.WriterTest;

public class ProgramRunnerClass {

  public static void main(String[] args) throws ParserConfigurationException {
    WriterTest A = new WriterTest();
    A.writeData("");

    Stock s1 = StockImpl.getBuilder().shareName("Google").purchaseValue(1000)
            .purchaseDate(new Date(2022, Calendar.JANUARY, 3))
            .quantity(10).stockSymbol("GOOG").create();
    Stock s2 = StockImpl.getBuilder().shareName("All").purchaseValue(1000)
            .purchaseDate(new Date(2022, Calendar.JANUARY, 10))
            .quantity(10).stockSymbol("ALL").create();
    List<Stock> s = new ArrayList<>();
    s.add(s1);
    s.add(s2);
    Portfolio p1 = PortfolioImpl.getBuilder().portfolioName("First_Portfolio").stocks(s).create();
    System.out.println(p1.getPortfolioValue(LocalDate.of(2022, 5, 3)));
  }
}
