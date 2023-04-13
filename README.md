#Automation Project
This is an automation project used to automate various tasks. The project is written in Java and uses the Selenium library for web automation.

#Description
This project includes automated tasks such as open a website, searching for product, add to card product, verify total amount, interacting with web pages, and automating business processes.

The Page Object Model (POM) framework is used to organize the test code into separate page objects, making it easier to maintain and update.

The TestNG framework is used for test management and reporting, providing a way to define test suites, run tests in parallel, and generate reports.

#Setup
To run this project, you will need to have Java 8 or higher installed on your system. You can download Java from the official website: https://www.java.com/en/download/

You will also need to install the required dependencies, including Selenium, by adding them to your project's classpath or using a build tool such as Maven or Gradle.

#Usage
You can go to TestCase.java, Right click and Run As TestNG Test. The system will run all test cases have Annotation is @Test. Or you can setup test case run order by adding priority

Test Data Source: I created TestData.properties File to store all Test Data

I also created ConfigPropertiesFile.java to get Test Data from TestData.properties file ( using key-value)

#Time Spent
This task was completed in approximately 3 hours. The majority of the time was spent for get locator of elements. Because this website is designed elements with locator that is a bit difficult to get