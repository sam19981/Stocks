package Controller;

import java.io.Console;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
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
        boolean mainQuit = false;
        while(!mainQuit) {
            view.showPreLoginOptions();
            String option = in.nextLine().toUpperCase();
            boolean quit = false;
            switch (option) {
                case "N":
                    while (!quit) {
                        view.showStringEntry();
                        String username = in.nextLine();
                        while (!model.checkValidUserName(username)) {
                            view.pleaseUseAValidUserName();
                            view.showStringEntry();
                            username = in.nextLine();
                        }
                        view.pleaseEnterString("password");
                        String pass = fetchValidPassword();
                        while (!model.checkValidPassword(pass)) {
                            view.pleaseEnterAValidPassword();
                            view.pleaseEnterString("password");
                            pass = fetchValidPassword();
                        }
                        quit = true;
                        view.showGreeting(username);
                        view.showDataEntryOptions();
                        getInputStyle(username, pass);
                    }
                    break;

                case "E":
                    while (!quit) {
                        view.showStringEntry();
                        String userName = in.nextLine();
                        view.pleaseEnterString("password");
                        String pass = fetchValidPassword();
                        while (!model.checkValidPassword(pass)) {
                            view.pleaseEnterAValidPassword();
                            view.pleaseEnterString("password");
                            pass = fetchValidPassword();
                        }
                        quit = setUser(userName,pass);
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

    public boolean setUser(String userName, String pass) {
        int status = model.setUser(userName, pass);
        if (status >= 0) {
            view.showGreeting(userName);
            performUserOperation();
            return true;
        } else if(status==-1) {
            view.pleaseInputCorrectDetails("Name/Password");
        } else if(status==-2) {
            view.pleaseInputCorrectDetails("File");
        }
        return false;
    }

    public String fetchValidPassword() {
        String pass;
        Console console = System.console();
        if(console!=null) {
            pass = new String(console.readPassword("Enter password: "));
        }
        else {
            pass = in.nextLine();
        }
        return pass;
    }

    public void getInputStyle(String username, String pass) {
        String style = in.nextLine().toUpperCase();
        boolean mainQuit = false;
        while(!mainQuit) {
            switch (style) {
                case "F":
                    parseFromFile(username, pass);
                    mainQuit = true;
                    break;
                case "I":
                    model.createUser(username, pass, "");
                    getPortfolioInformation();
                    getStocksInformation();
                    view.showGreeting(username);
                    performUserOperation();
                    mainQuit = true;
                    break;
                default:
                    view.showOptionError();
                    view.showDataEntryOptions();
                    style = in.nextLine();
            }
        }
    }

    @Override
    public void parseFromFile(String username, String pass) {
        boolean quit = false;
        String fileName = "";
        while(!quit) {
            view.fileInstructions();
            view.pleaseEnterString("file from which you want to input information");
            fileName = in.nextLine();
            if(model.checkValidFile(fileName)==0) {
                quit = true;
            }
            else {
                view.pleaseInputCorrectDetails("file from which you want to input information");
            }
            if(model.createUser(username, pass, fileName)>=0) {
                quit &= setUser(username, pass);
            }
            else{
                quit = false;
            }
        }
    }

    @Override
    public void getPortfolioInformation(){
        boolean quit = false;
        while(!quit) {
            view.getNoPortfolios();
            String number = in.nextLine();
            float n=model.checkValidNumber(number);
            if ((n)!=-1) {
                quit = true;
                view.getPortfolioNames();
                for(int i=1; i<=n; i++){
                    view.getPortfolioNumber(String.valueOf(i));
                    String PName = in.nextLine();
                    while(model.addPortfolios(PName)==-1) {
                        view.pleaseUseADifferentPortfolioName();
                        view.getPortfolioNumber(String.valueOf(i));
                        PName = in.nextLine();
                    }
                }
            }
            else{
                view.pleaseInputCorrectDetails("Portfolio Number");
            }
        }
    }

    @Override
    public void getStocksInformation() {
        boolean mainQuit = false;
        String year="", month="", date="";
        String quantity = "0";
        while(!mainQuit) {
            List<Portfolio> portfolios = model.getUserPortFolios();
            for (Portfolio p : portfolios) {
                if (p.getAllStocks().isEmpty()) {
                    view.getStockforPortfolio(p.getPortfolioName());
                    String number = in.nextLine();
                    while(model.checkValidNumber(number)<=0) {
                        view.getStockforPortfolio(p.getPortfolioName());
                        number = in.nextLine();
                    }
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
                            view.pleaseEnterString("valid Symbol");
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
    public void performUserOperation() {
        boolean mainQuit = false;
        while (!mainQuit) {
            view.showUserOperations();
            String operation = in.nextLine().toUpperCase();
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
                            view.displayIndividualPortfolio(model.getUserPortFolios(), PortfolioName);
                            view.displayPortfolioResults(res);
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
                case "L":
                    mainQuit = true;
                    break;
                default:
                    view.showOptionError();
                    break;
            }
        }
    }

}
