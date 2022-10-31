package View;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import Models.Portfolio;
import Models.Stock;

public class TextView implements IView {
    private final Appendable  out;

    public TextView(PrintStream out) {
        this.out = out;
    }

    public void showString(String s) throws IOException {
        out.append(s);
    }

    public void showPreLoginOptions() throws IOException {
        //print the UI
        createSpace();
        out.append("Menu: ");
        out.append("N: Are you a new user?");
        out.append("E: Are you an existing user?");
        out.append("Q: Quit the program");
        out.append("Enter your choice: ");
    }



    public void showStringEntry() throws IOException {
        out.append("\nEnter your unique username: ");
    }

    @Override
    public void showDateEntryOptions() throws IOException {
        out.append("Menu: ");
        out.append("F: Do you wish to provide your information in form of a file?");
        out.append("I: Do you wish to input your Stock information step-by-step?");
        out.append("Q: Quit the program");
        out.append("Enter your choice: ");
    }

    public void showOptionError() throws IOException {
        out.append("\nInvalid option. Please try again.");
    }

    @Override
    public void getNoPortfolios() throws IOException {
        out.append("How many Portfolios do you wish to hold?");
    }

    @Override
    public void showUserDetailsFetched() throws IOException {
        out.append("Completed fetching of user details");
        try {
            Thread.sleep(100);
        } catch(InterruptedException e) {
            System.out.append("Problem fetching data");
        }
    }

    @Override
    public void showUserOperations() throws IOException {
        out.append("Menu: ");
        out.append("S: Do you want to see all the portfolios under your name?");
        out.append("C: Do you wish to compute the values of any portfolios?");
        out.append("A: Do you wish to add new portfolios?");
        out.append("B: Go back to the previous menu");
        out.append("Enter your choice: ");
    }

    @Override
    public void displayPortfolios(List<Portfolio> portfolio) throws IOException {
        out.append("These are your Portfolio details:");
        for(Portfolio p: portfolio) {
            out.append(p.getPortfolioName());
            List<Stock> Stocks = p.getAllStocks();
            for(Stock s: Stocks){
                out.append("----"+s.getStockName());
            }
        }
    }

    public void fetchPortfolioForComputation() throws IOException {
        out.append("Which of the above Portfolio's value do you wish to see?");
    }

    @Override
    public void displayString(String s) throws IOException {
        out.append(s);
    }

    @Override
    public void fetchDate() throws IOException {
        out.append("Please input the date for computation");
    }

    @Override
    public void fetchMonth() throws IOException {
        out.append("Please input the month for computation");
    }

    @Override
    public void pleaseInputCorrectDetails(String d) throws IOException {
        out.append("Please input correct "+d);
    }

    @Override
    public void pleasePickADifferentUserName() throws IOException {
        out.append("Please pick a different username");
    }

    @Override
    public void pleaseEnterAValidPassword() throws IOException {
        out.append("Please enter a valid password");
    }

    @Override
    public void fetchYear() throws IOException {
        out.append("Please input the year for computation");
    }

    @Override
    public void getPortfolioNames() throws IOException {
        out.append("Please enter the names of your portfolios");
    }

    @Override
    public void getPortfolioNumber(String n) throws IOException {
        out.append("Please enter the name of portfolio "+n);
    }

    @Override
    public void getStockforPortfolio(String pName) throws IOException {
        out.append("Please input the number of Stocks for Portfolio: "+ pName);
    }

    @Override
    public void printString(String s) throws IOException {
        out.append(s);
    }

    @Override
    public void pleaseEnterString(String s) throws IOException {
        out.append("Please enter the "+s);
    }

    @Override
    public void createSpace() throws IOException {
//        out.append();
        out.append("-------------------");
//        out.append();
    }

}
