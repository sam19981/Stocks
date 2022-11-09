Our program provides the user with the following features/ functionalities:

1. Ability to store his portfolio and stock information and fetch it back when he logs in again.
2. Provides the user option to input his details through an xml/ txt file.
3. Also, provides the user option to input his details step by step using the Text UI.
4. Get Portfolio - Displays the specific portfolios and the stocks present in it which the user
 wants to examine.
5. Compute Portfolio - Computes and returns the worth of the single portfolio of the user on a
 given date.
6. Add Portfolio - Add new portfolio with the desired set of stocks which the user wants.
7. Get All Portfolios - Displays all the portfolios the user has and all the stocks present
 in them with their costs when they were purchased.
8. If 2 stocks in the same portfolio have the same name their quantities add up to show the total
amount.
9. If the user tries to input a new Portfolio with the same name as one of his existing portfolio,
we request him to provide a different name.
10. Allows a user to add new portfolios to his name if he/she wishes even after an initial input
 with file or step-by-step input is complete.


Features which are implemented but the functionality is not provided to the user:
1. Compute All Portfolios - Computes the total worth of the portfolios which user has
on a given date.
2. Sell Stock - Remove a stock from a given portfolio owned by the user.
3. Add Stock - Add a stock to the given portfolio after initial creation of portfolio.


Things to be implemented in the future:
1. Sell Portfolio - The user can sell his portfolio and determine the amount of profit/loss
he make at the given date.
2. Remove all Portfolios - Sell all the portfolios owned by the user and display
the total profit made by the portfolios on the sell date.
3. Compute the loss/ profit on each stock if sold on a particular day before the user sells it.
4. Increased protection to the user data, currently the user name and password is stored just as
strings in the csv. Encryption could be used to encrypt the password before storing.
5. Currently only a new user who has no data can provide a file input to store data, we plan to
add this feature to an existing user as well.
6. If 2 stocks in the same portfolio have the same name their quantities add up to show the total
 amount, this however does not take care of the dates on which they were purchased.
 Though not relevant to this assignment we would need to consider this if we are selling the
 portfolio or the stock in the future assignments.