package View;

import java.io.IOException;
import java.util.List;

import Models.Portfolio;

public interface IView {
    void showString(String s) throws IOException;
    void showPreLoginOptions() throws IOException;
    void showDateEntryOptions() throws IOException;
    void showStringEntry() throws IOException;
    void showOptionError() throws IOException;
    void getNoPortfolios() throws IOException;
    void showUserDetailsFetched() throws IOException;
    void showUserOperations() throws IOException;
    void displayPortfolios(List<Portfolio> p) throws IOException;
    void fetchPortfolioForComputation() throws IOException;
    void displayString(String i) throws IOException;
    void fetchDate() throws IOException;

    void pleaseEnterAValidPassword() throws IOException;

    void fetchYear() throws IOException;
    void fetchMonth() throws IOException;
    void pleaseInputCorrectDetails(String d) throws IOException;
    void pleasePickADifferentUserName() throws IOException;
    void getPortfolioNames() throws IOException;
    void getPortfolioNumber(String n) throws IOException;
    void getStockforPortfolio(String pName) throws IOException;
    void printString(String s) throws IOException;

  void pleaseEnterString(String s) throws IOException;

  void createSpace() throws IOException;
}
