package View;

import java.util.List;

import Models.Portfolio;

public interface IView {
    void showString(String s);
    void showPreLoginOptions();
    void showDateEntryOptions();
    void showStringEntry();
    void showOptionError();
    void getNoPortfolios();
    void showUserDetailsFetched();
    void showUserOperations();
    void displayPortfolios(List<Portfolio> p);
    void fetchPortfolioForComputation();
    void displayString(String i);
    void fetchDate();

    void pleaseEnterAValidPassword();

    void fetchYear();
    void fetchMonth();
    void pleaseInputCorrectDetails(String d);
    void pleasePickADifferentUserName();
    void getPortfolioNames();
    void getPortfolioNumber(String n);
    void getStockforPortfolio(String pName);
    void printString(String s);
    void createSpace();
}
