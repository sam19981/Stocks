import java.util.Date;

public class ShareImpl implements Share{
  public String shareName;
  public Date purchaseDate;
  public Integer purchaseValue;
  public Integer quantity;

  ShareImpl(String s, Date pd, Integer pV, Integer q){
    shareName = s;
    purchaseDate = pd;
    purchaseValue = pV;
    quantity = q;
  }

  ShareImpl(){
    shareName = "";
    purchaseDate = new Date();
    purchaseValue = 0;
    quantity = 0;
  }
}
