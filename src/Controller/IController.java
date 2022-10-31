package Controller;

public interface IController {
    void go();

    void parseFromFile(String username, String pass);

    void getPortfolioInformation();
    void getStocksInformation();
    void performUserOperation();
}
