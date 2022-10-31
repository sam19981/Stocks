package View;

import java.util.List;

import Models.Portfolio;

public interface IView {
    void showPreLoginOptions();
    void showDataEntryOptions();
    void showStringEntry();
    void showOptionError();
    void getNoPortfolios();
    void showUserDetailsFetched();
    void showUserOperations();
    void displayPortfolios(List<Portfolio> p);

    void displayIndividualPortfolio(List<Portfolio> portfolio, String portfolioName);

    void displayPortfolioResults(float result);

    void fetchPortfolioForComputation();
    void displayString(String i);
    void fetchDate();
    void pleaseEnterAValidPassword();
    void fetchYear();
    void fetchMonth();
    void pleaseInputCorrectDetails(String d);
    void pleaseUseAValidUserName();
    void pleasePickADifferentUserName();
    void getPortfolioNames();
    void getPortfolioNumber(String n);
    void getStockforPortfolio(String pName);
    void pleaseEnterString(String s);
    void createSpace();
    void pleaseUseADifferentPortfolioName();
    void fileInstructions();
    void showGreeting(String username);
}
