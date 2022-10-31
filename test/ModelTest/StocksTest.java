package ModelTest;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.Random;
import Models.Stock;
import Models.StockImpl;
import static org.junit.Assert.assertEquals;

public class StocksTest {
  Stock s;

  @Before
  public void setup() {
    s = StockImpl.getBuilder().stockName("Google")
            .purchaseDate(LocalDate.of(2020,3,20)).stockSymbol("GOOG")
            .quantity(10)
            .create();
  }

  @Test
  public void TestBaseValues() {
    assertEquals(s.getStockName(), "Google");
    assertEquals(s.getStockSymbol(), "GOOG");
    assertEquals(s.getPurchaseDate(), LocalDate.of(2020, 3, 20));
    assertEquals(s.getQuantity(), 10, 0);
  }

  @Test
  public void TestGetPurchaseValue() {
    assertEquals(s.getPurchaseValue(),
            s.getValue(LocalDate.of(2020,3,20)),0);
  }

  @Test
  public void TestIncreaseQuality() {
    Random r = new Random(100);
    for(int i =0; i<50; i++) {
      int added = r.nextInt();
      assertEquals(s.getQuantity()+added, s.increaseQuantity(added).getQuantity(), 0);
    }
  }

 @Test
  public void BlankTestGetValue() {
    Stock s = StockImpl.getBuilder().create();
   assertEquals(s.getStockName(), "");
   assertEquals(s.getStockSymbol(), "");
   assertEquals(s.getPurchaseDate(), LocalDate.now().minusDays(1));
   assertEquals(s.getQuantity(), 0, 0);
   assertEquals(s.getPurchaseValue(), 0, 0);
 }

  @Test
  public void TestGetValueRepeatedCalls() {
    Stock s = StockImpl.getBuilder().stockSymbol("GOOG").create();
    assertEquals(s.getValue(LocalDate.now().minusDays(1)),
            s.getValue(LocalDate.now().minusDays(1)), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestGetValueInvalidStock() {
    Stock s = StockImpl.getBuilder().stockSymbol("GOO").create();
    s.getValue(LocalDate.now().minusDays(1));
  }

}
