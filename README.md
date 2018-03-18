# Tajawal-Flight-Automation-Demo-Selenium
Tajawal Flights automation demo contains basic 2 scenarios automated using selenium webdriver, testng and Java implemented with Page Object Model 

Salient features of the project
Web automation framework using Selenium webdriver and testng framework with Java
POM based framework for code to be easily expandable,  maintainable and reusable
Maven build project and dependency
Usage of properties config file for configuration settings
HTML report for the every test/suite run result
Script takes screenshot for every passed/failed test cases
Proper logging for every test case/suite started, passed, failed or skipped using logback.xml
Fetching required data from website and save it in a .CSV file as required
Executable based on dynamic URL (www.tajawal.ae, www.tajawal.com )
Project deployed on public git repository for easy access and collaboration
Can be executed on different browsers like chrome, firefox

Explanation of framework - 
This framework is designed using POM approch using selenium and Java. There are three folders inside SCR folder which are -  'Main' 'Test' and 'Resources' 

1 . Main folder - Main folder has important three below classes - 
BaseFunctions - It is a abstract classes having all common functions.
ListenersMethods - Implements ITestListener and ISuiteListener interfaces to generate logs or customize the Testing reports for every test-case/suite executed.
PageobjectClasees - These classes have all the elements and functions to take action on different screens like home screen, listing screen, Review and details screen.

2. Test folder - Tests folder has two test classes - 
'VerifySearchParamsAndFillCustomerInfo' - Test case flow for verifying search parameters on listing screen and filling customer information on details and review screen. 
'VerifySortingOnListing'  - Test case flow for verify sorting flights from low to high and storing the prices in a .csv file.

3. Resources folder - Contains all the Resources files like testng.xml, config.properties, logback.xml files.


Test case covered -
'VerifySearchParamsAndFillCustomerInfo' - Below is the flow of the test case
Open Url > Enter Search parameters on home screen > Navigate and Verify On Listing Screen > Fetch flight details and navigate to travelers details page >  Enter customer Details > Continue to Payment
'VerifySearchParameters' - Below is the flow of the test case
Open Url > Enter Search parameters on home screen > Navigate and Verify On ListingScreen > Fetch flight details and price > Sort listing prices low to high and store in a .csv file
