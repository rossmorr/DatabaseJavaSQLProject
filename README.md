Coverage: 75%
# IMS PROJECT --- ROSS MORRISON

This is the full implementation of an inventory management system. It is used to perform CRUD operations (create, read, update, delete) on entries in a database.
There are three entry categories on which you can perform these operations (Customer, Item, Order).
Navigate to any of these sub-menus by inputting commands as prompted by the system and choose an operation.
If an order is created then you will also be prompted to set an initial item and if there are no items in the order, the order will be deleted.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.



### Prerequisites

MySQL workbench (or alternative)
an SQL database is required in order to use the software. MySQL was the software of choice during development and therefore it is recommended. Full details of how to install can be
found here https://dev.mysql.com/doc/mysql-getting-started/en/#mysql-getting-started-installing. Make sure that port is set to 3306, username is "root" and password is "pass"
or you will not be able to connect to the database.

### Installing

A step by step series of examples that tell you how to get a development env running

The integrated development environment of choice for this project was Eclipse. In order to install this, carry out the following steps.
-Download the eclipse installer from this website - http://www.eclipse.org/downloads
-Run the installer and when selecting a package, choose "Eclipse IDE For Java Developers"
-Choose a directory and hit install

You can either run the code through eclipse or by directly running the jar file included.
if you navigate to open project in the toolbar you can navigate to and open the folder for this project, at which point you can view and edit the source code or run the prebuild software.



## Running the tests

In order to run the tests on the latest version of eclipse, right click the directory of the project and in the run as sub-menu click junit project.
The IDE will then run all of the tests and give you the results and names of each of the tests.

### Unit Tests 

The unit tests test CRUD functionality of the database

for example, the unit test in the testitemDAO class (create) tests the functionality of the system for creating an item in the Database.
A dummy database is created and a create command is executed on that database, the test then asserts whether or not the code successfully ran and was successful.

This is the same for all the DAO packages, with order having some additional testing for adding items to an order, deleting items from an order and calculating the cost of an order.

### Integration Tests 

Integration tests were done through mockito and test the functionality of the program from the controller level (the controllers call the DAOs).
These tests run with the rest of the unit tests through Junit and can be used to check the stability of the code by mocking dependancies within these controllers.
An example of an integration test is running the create function through the customer controller. This mocks the dependancy of the database and any calls to external
implementation. It then runs all of the code for the create functionality, substituting those dependancies in.


## Deployment

You must have java installed if you want to run the jar file included with the project. https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/
This website has detailed instructions for installation. Once installed you can 
type java -jar "INSERTFILEPATHHERE" to run the jar file. The system will then start.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Ross Morrison** - *item/order/testing implementation*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* lots of support from my QA Cohort
