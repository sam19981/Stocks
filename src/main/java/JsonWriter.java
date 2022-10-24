import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sound.sampled.Port;

class UserCollection{
  public List<UserTemp> users;

  UserCollection(){

  }
}

class UserTemp
{
  public String userName;
  public List<PortfolioTemp> portfolios;

  UserTemp(){

  }
}

class PortfolioTemp{
  public List<ShareTemp> shares;

  PortfolioTemp(){

  }
}

class ShareTemp{
  public String shareName;
  public Date purchaseDate;
  public Integer purchaseValue;
  public Integer quantity;

  ShareTemp(String s, Date pd, Integer pV, Integer q){
    shareName = s;
    purchaseDate = pd;
    purchaseValue = pV;
    quantity = q;
  }

  ShareTemp(){

  }
}


public class JsonWriter {

  public static void main(String[] args) {

    UserTemp u1 = new UserTemp();
    u1.userName = "Karthik";

    PortfolioTemp p1 = new PortfolioTemp();

    ShareTemp s1 = new ShareTemp("Google",new Date(2022, Calendar.JANUARY, 3),100, 10);
    ShareTemp s2 = new ShareTemp("Google",new Date(2022, Calendar.FEBRUARY, 10),200, 50);
    ShareTemp s3 = new ShareTemp("Google",new Date(2022, Calendar.DECEMBER, 13),50, 12);

    List<ShareTemp> shares = new ArrayList<>();
    shares.add(s1);
    shares.add(s2);
    shares.add(s3);

    p1.shares = shares;

    PortfolioTemp p2 = new PortfolioTemp();

    ShareTemp s4 = new ShareTemp("Apple",new Date(2022, Calendar.NOVEMBER, 13),70, 5);

    List<ShareTemp> shares2 = new ArrayList<>();
    shares2.add(s4);

    p2.shares = shares2;

    List<PortfolioTemp> tempList = new ArrayList<>();
    tempList.add(p1);
    tempList.add(p2);

    u1.portfolios = tempList;

    //User 2
    UserTemp u2 = new UserTemp();
    u2.userName = "Sankar";

    PortfolioTemp p3 = new PortfolioTemp();

    ShareTemp s6 = new ShareTemp("Google",new Date(2022, Calendar.MAY, 3),800, 20);
    ShareTemp s7 = new ShareTemp("Google",new Date(2022, Calendar.MARCH, 10),80, 50);

    List<ShareTemp> shares3 = new ArrayList<ShareTemp>();
    shares3.add(s6);
    shares3.add(s7);

    p3.shares = shares3;


    List<PortfolioTemp> tempList2 = new ArrayList<>();
    tempList2.add(p3);

    u2.portfolios = tempList2;

    UserCollection userCollection = new UserCollection();

    List<UserTemp> usersList = new ArrayList<>();
    usersList.add(u1);
    usersList.add(u2);

    userCollection.users = usersList;

    ObjectMapper mapper = new ObjectMapper();

    try {

      // Writing to a file
      mapper.writeValue(new File("output/Output.json"),
              userCollection );

    } catch (IOException e) {
      e.printStackTrace();
    }

    try {

      // Writing to a file
      UserCollection userCollection1 = mapper.readValue(new FileReader("output/Output.json"), UserCollection.class);
      for(UserTemp uc: userCollection1.users){
        for(PortfolioTemp p: uc.portfolios){
          for(ShareTemp s: p.shares){
            System.out.println(s.shareName);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
