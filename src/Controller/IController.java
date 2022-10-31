package Controller;

import java.io.IOException;

public interface IController {
    void go() throws IOException;
    void getPortfolioInformation() throws IOException;
    void getStocksInformation() throws IOException;
    void performUserOperation() throws IOException;
}
