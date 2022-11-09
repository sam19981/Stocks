The hierarchy (MVC) of the program is as follows:

I. The Model class in model package fetches all user details at run time by instantiating the data Model
classes User,Portfolio and stock.
  i. Our data models come with builders which we use to instantiate objects and thus avoiding
  any error or empty data during their creation.

Justification:
We chose to use the above design even when the question did not specify anything
about multiple users because this would help us in the future assignments.
In case Stocks Part-2 expands to multiple users working on the same program
or asking us to capture and store data for multiple data, our program will be easy to extend.

II. Each User object contains a name, password and one or more portfolios assigned to his name.

Justification:
A User object is created with a uniques username and password so that
data of one user does not interfere with data of other users

III. Each Portfolio object has its unique name, and a list of Stock Objects within it.

Justification:
A Portfolio is made to be identified uniquely by a portfolio name so that a user
can choose which portfolio's value to compute from when he wishes to

IV. Each Stock Object has information about a given stock i.e Stock name, ticker symbol,
purchase date, etc.

Justification:
A Stock Object not only has a name, ticker symbol and quantity but also has the purchase date
so that if future assignments ask us to compute profit or loss at a particular date
we will be able to do it.

V. As input maybe provided to the program in xml we have an XML parser which
parses information and loads it onto the program.

We chose XML because:
a. XML offers the capability to display data because it is a markup language.
JSON supports only text and number data type.

b. In jdk, javax.xml.parsers offers us help to better parse data in XML.
Whereas for json there was no direct help available in jdk.

VI. We also have an XML Writer class which stores the information of the user onto
 a file related to the user.

Justification:
We have a writer along with the reader as data fetched during the interaction with the user
could be written to the user related file .


vii. A Connection class is present with one Impl using the Yahoo API, could be
extended to more classes in the future assignments.

Justification:
If Alpha Vantage is to be supported in the future, we can easily create
an implementation of the Connection class and return the Stream of data to the program.

viii. A Utilities class is present to load CSV data about the users onto the program.

ix. A View class is present whose implementation's methods are called each time we want the user
 to see some information.

 Justification:
 The View is separated from other components so that it becomes easy to narrow down any
 problems with the actual functionality.

x. Finally, a Controller class which handles the running of all of these classes,
i.e delegates work to each of these classes appropriately for the program to function.

The controller is the entity that takes input from the user,
 which in turn causes the model to transition state.



