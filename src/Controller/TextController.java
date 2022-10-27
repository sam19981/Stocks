package Controller;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import Models.IModel;
import Models.Portfolio;
import View.IView;

public class TextController implements IController{
    private final Scanner in;
    private final IView view;
    private final IModel model;

    public TextController(IModel model,InputStream in,IView view) {
        this.model = model;
        this.view = view;
        this.in = new Scanner(in);

    }

    @Override
    public void go() {
        view.showPreLoginOptions();
        String option = in.nextLine();
        boolean quit = false;
        switch (option) {
            case "N":
                while(!quit) {
                    view.showStringEntry();
                    String input = in.nextLine();
                    view.showString("Please enter your password");
                    String pass = in.nextLine();
                    if(model.createUser(input, pass)!=-1) {
                        quit = true;
                        view.showDateEntryOptions();
                        getInputStyle();
                        view.displayPortfolios(model.getUserPortFolios());
                    }
                    else {
                        view.pleasePickADifferentUserName();
                    }
                }
                break;

            case "E":
                while(!quit) {
                    view.showStringEntry();
                    String userName = in.nextLine();
                    view.showString("Please enter your password");
                    String pass = in.nextLine();
                    if (model.setUser(userName, pass) != -1) {
                        quit = true;
                        view.showUserDetailsFetched();
                        view.showUserOperations();
                        String operation = in.nextLine();
                        performUserOperation(operation);
                    }
                    else {
                        view.pleaseInputCorrectDetails("Name/Password");
                    }
                }
                break;

            case "Q":
                return;
            default:
                view.showOptionError();
        }
    }

    public void getInputStyle() {
        String style = in.nextLine();
        switch(style) {
            case "F":
                parseFromFile();
                break;
            case "I":
                getPortfolioInformation();
                getStocksInformation();
                break;
        }
    }

    public void parseFromFile() {

    }

    @Override
    public void getPortfolioInformation(){
        boolean quit = false;
        while(!quit) {
            view.getNoPortfolios();
            String number = in.nextLine();
            int n=model.checkValidNumber(number);
            if ((n)!=-1) {
                quit = true;
                view.getPortfolioNames();
                for(int i=1; i<=n; i++){
                    view.getPortfolioNumber(String.valueOf(i));
                    String PName = in.nextLine();
                    model.addPortfolios(PName);
                }
            }
            else{
                view.pleaseInputCorrectDetails("Portfolio Number");
            }
        }
    }

    @Override
    public void getStocksInformation() {
        List<Portfolio> portfolios = model.getUserPortFolios();
        for(Portfolio p: portfolios) {
            if(p.getAllStocks().isEmpty()) {
                view.getStockforPortfolio(p.getPortfolioName());
                String number = in.nextLine();
                for (int i = 0; i < Integer.parseInt(number); i++) {
                    view.printString("Please enter the Stock Name");
                    String sName = in.nextLine();
                    view.printString("Please enter the quantity");
                    String quantity = in.nextLine();
                    view.printString("Please enter the purchase Date");
                    String date = in.nextLine();
                    view.printString("Please enter the purchase Month");
                    String month = in.nextLine();
                    view.printString("Please enter the purchase Year");
                    String year = in.nextLine();
                    view.printString("Please enter the purchase value");
                    String value = in.nextLine();
                    view.printString("Please enter the stock symbol");
                    String symbol = in.nextLine();
                    model.addStock(p, model.createStock(sName, quantity,
                            date, month, year, value, symbol));
                }
            }
        }
    }

    @Override
    public void performUserOperation(String input){
        switch(input){
            case "S":
                view.displayPortfolios(model.getUserPortFolios());
                break;
            case "C":
                boolean quit = false;
                while(!quit) {
                    view.displayPortfolios(model.getUserPortFolios());
                    view.fetchPortfolioForComputation();
                    String PortfolioName = in.nextLine();
                    view.fetchDate();
                    String date = in.nextLine();
                    view.fetchMonth();
                    String month = in.nextLine();
                    view.fetchYear();
                    String year = in.nextLine();
                    float res = model.computePortfolioValues(PortfolioName, model.createValidDate(year, month, date));
                    if(res!=-1){
                        quit = true;
                        view.displayString(String.valueOf(res));
                    }
                    else {
                        view.pleaseInputCorrectDetails("Details");
                    }
                }
                break;
            case "A":
                getPortfolioInformation();
                getStocksInformation();
                view.displayPortfolios(model.getUserPortFolios());
                break;
            default:
                view.showOptionError();
        }
    }

}
