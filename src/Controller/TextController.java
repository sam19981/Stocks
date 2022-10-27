package Controller;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Scanner;

import Models.IModel;
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

    public void go() {
        view.showPreLoginOptions();
        String option = in.nextLine();
        boolean quit = false;
        switch (option) {
            case "N":
                while(!quit) {
                    view.showStringEntry();
                    String input = in.nextLine();
                    if(model.createUser(input)!=-1) {
                        quit = true;
                        view.showDateEntryOptions();
                        getPortfolioInformation();
                    }
                    else {
                        view.pleasePickADifferentUserName();
                    }
                }
                break;

            case "E":
                while(!quit) {
                    view.showStringEntry();
                    String input = in.nextLine();
                    if (model.setUser(input) != -1) {
                        quit = true;
                        view.showUserDetailsFetched();
                        view.showUserOperations();
                        input = in.nextLine();
                        performUserOperation(input);
                    }
                    else {
                        view.pleaseInputCorrectDetails("Name");
                    }
                }
                break;

            case "Q":
                return;
            default:
                view.showOptionError();
        }
    }

    public void getPortfolioInformation(){
        boolean quit = false;
        while(!quit) {
            view.showStringEntry();
            view.getNoPortfolios();
            String number = in.nextLine();
            int n=model.checkValidNumber(number);
            /*if ((n)!=-1) {
                quit = true;
                view.getPortfolioNames();
                for(int i=0; i<n; i++){
                    view.getPortfolioNumber(i);
                }
            }
            else{
                view.pleaseInputCorrectDetails("Portfolio Number");
            }*/
        }
    }

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
            default:
                view.showOptionError();
        }
    }


}
