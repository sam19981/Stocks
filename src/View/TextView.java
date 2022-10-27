package View;

import java.io.PrintStream;
import java.util.List;

import Models.Portfolio;
import Models.Stock;

public class TextView implements IView {
    private final PrintStream out;

    public TextView(PrintStream out) {
        this.out = out;
    }

    public void showString(String s) {
        out.println("String: "+s);
    }

    public void showPreLoginOptions() {
        //print the UI
        out.println("Menu: ");
        out.println("N: Are you a new user?");
        out.println("E: Are you an existing user?");
        out.println("Q: Quit the program");
        out.print("Enter your choice: ");
    }



    public void showStringEntry() {
        out.print("\nEnter your unique username: ");
    }

    @Override
    public void showDateEntryOptions() {
        out.println("Menu: ");
        out.println("F: Do you wish to provide your information in form of a file?");
        out.println("I: Do you wish to input your Stock information step-by-step?");
        out.println("Q: Quit the program");
        out.print("Enter your choice: ");
    }

    public void showOptionError() {
        out.print("\nInvalid option. Please try again.");
    }

    @Override
    public void getNoPortfolios() {
        out.println("How many Portfolios do you wish to hold?");
    }

    @Override
    public void showUserDetailsFetched() {
        out.println("Completed fetching of user details");
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            System.out.println("Problem fetching data");
        }
    }

    @Override
    public void showUserOperations() {
        out.println("Menu: ");
        out.println("S: Do you want to see all the portfolios under your name?");
        out.println("C: Do you wish to compute the values of any portfolios?");
        out.println("Q: Quit the program");
        out.print("Enter your choice: ");
    }

    @Override
    public void displayPortfolios(List<Portfolio> portfolio) {
        for(Portfolio p: portfolio) {
            out.println(p.getPortfolioName());
            List<Stock> Stocks = p.getAllStocks();
            for(Stock s: Stocks){
                out.println("----"+s.getStockName());
            }
        }
    }

    public void fetchPortfolioForComputation() {
        out.println("Which of the above Portfolio's value do you wish to see?");
    }

    @Override
    public void displayString(String s) {
        out.println(s);
    }

    @Override
    public void fetchDate() {
        out.println("Please input the date for computation");
    }

    @Override
    public void fetchMonth() {
        out.println("Please input the month for computation");
    }

    @Override
    public void pleaseInputCorrectDetails(String d) {
        out.println("Please input correct "+d);
    }

    @Override
    public void pleasePickADifferentUserName() {
        out.println("Please pick a different username");
    }

    @Override
    public void fetchYear() {
        out.println("Please input the year for computation");
    }

    @Override
    public void getPortfolioNames() {
        out.println("Please enter the names of your portfolios");
    }

    @Override
    public void getPortfolioNumber() {

    }

}
