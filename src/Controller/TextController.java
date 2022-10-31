package Controller;

import java.io.IOException;
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

    public TextController(IModel model,Readable in,IView view) {
        this.model = model;
        this.view = view;
        this.in = new Scanner(System.setIn();));
    }

    @Override
    public void go() throws IOException {
        boolean mainQuit = false;
        while(!mainQuit) {
            view.showPreLoginOptions();
            String option = in.nextLine();
            boolean quit = false;
            switch (option) {
                case "N":
                    while (!quit) {
                        view.showStringEntry();
                        String input = in.nextLine();
                        view.showString("Please enter your password");
                        String pass = in.nextLine();
                        int status = model.createUser(input, pass);
                        if (status >= 0) {
                            quit = true;
                            view.showDateEntryOptions();
                            getInputStyle();
                            view.displayPortfolios(model.getUserPortFolios());
                            view.showUserDetailsFetched();
                            performUserOperation();
                        } else if(status==-1) {
                            view.pleasePickADifferentUserName();
                        }
                        else if(status==-2) {
                            view.pleaseEnterAValidPassword();
                        }
                    }
                    break;

                case "E":
                    while (!quit) {
                        view.showStringEntry();
                        String userName = in.nextLine();
                        view.showString("Please enter your password");
                        String pass = in.nextLine();
                        if (model.setUser(userName, pass) != -1) {
                            quit = true;
                            view.showUserDetailsFetched();
                            performUserOperation();
                        } else {
                            view.pleaseInputCorrectDetails("Name/Password");
                        }
                    }
                    break;

                case "Q":
                    mainQuit = true;
                    break;
                default:
                    view.showOptionError();
            }
        }
    }

    public void getInputStyle() throws IOException {
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
    public void getPortfolioInformation() throws IOException {
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
    public void getStocksInformation() throws IOException {
        boolean mainQuit = false;
        String year="", month="", date="";
        String quantity = "0";
        while(!mainQuit) {
            List<Portfolio> portfolios = model.getUserPortFolios();
            for (Portfolio p : portfolios) {
                if (p.getAllStocks().isEmpty()) {
                    view.getStockforPortfolio(p.getPortfolioName());
                    String number = in.nextLine();
                    for (int i = 0; i < Integer.parseInt(number); i++) {
                        view.pleaseEnterString("Stock Name");
                        String sName = in.nextLine();
                        boolean quit = false;
                        while(!quit) {
                            view.pleaseEnterString("quantity");
                            quantity = in.nextLine();
                            if(model.checkValidNumber(quantity)>=0){
                                quit = true;
                            }
                            else{
                                view.pleaseInputCorrectDetails("Quantity");
                            }
                        }
                        boolean dateQuit = false;
                        while(!dateQuit) {
                            quit = false;
                            while (!quit) {
                                view.pleaseEnterString("purchase Date");
                                date = in.nextLine();
                                if (model.checkValidNumber(date) >= 0) {
                                    quit = true;
                                }
                                else{
                                    view.pleaseInputCorrectDetails("Date");
                                }
                            }
                            quit = false;
                            while (!quit) {
                                view.pleaseEnterString("purchase Month");
                                month = in.nextLine();
                                if (model.checkValidNumber(month) >= 0) {
                                    quit = true;
                                }
                                else{
                                    view.pleaseInputCorrectDetails("Month");
                                }
                            }
                            quit = false;
                            while (!quit) {
                                view.pleaseEnterString("purchase Year");
                                year = in.nextLine();
                                if (model.checkValidNumber(year) >= 0) {
                                    quit = true;
                                }
                                else{
                                    view.pleaseInputCorrectDetails("Year");
                                }
                            }
                            if(model.createValidDate(year, month, date)!=null) {
                                dateQuit = true;
                            }
                            else{
                                view.pleaseInputCorrectDetails("Date");
                            }
                        }
                        view.pleaseEnterString("stock symbol");
                        String symbol = in.nextLine();
                        while(model.validateStocksymbol(symbol)==0)
                        {
                            view.printString("Please Enter a valid Symbol");
                            symbol = in.nextLine();
                        }
                        model.addStock(p, model.createStock(sName, quantity,
                                date, month, year, symbol));
                        mainQuit = true;
                    }
                }
            }
        }
    }

    @Override
    public void performUserOperation() throws IOException {
        boolean mainQuit = false;
        while (!mainQuit) {
            view.showUserOperations();
            String operation = in.nextLine();
            switch (operation) {
                case "S":
                    view.displayPortfolios(model.getUserPortFolios());
                    break;
                case "C":
                    boolean quit = false;
                    while (!quit) {
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
                        if (res != -1) {
                            quit = true;
                            view.displayString(String.valueOf(res));
                        } else {
                            view.pleaseInputCorrectDetails("Details");
                        }
                    }
                    break;
                case "A":
                    getPortfolioInformation();
                    getStocksInformation();
                    view.displayPortfolios(model.getUserPortFolios());
                    break;
                case "B":
                    mainQuit = true;
                    break;
                default:
                    view.showOptionError();
            }
        }
    }

}
