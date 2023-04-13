package testRunners;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dataProvider.ConfigPropertiesFile;
import pageObject.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestCase {

	WebDriver driver;
	ConfigPropertiesFile pros = new ConfigPropertiesFile();

	@BeforeMethod
	public void setUp() {
		// Setup Chrome Options
		ChromeOptions options = new ChromeOptions();
		// Add Chrome incognito mode
		options.addArguments("-incognito");
		// Add Chrome start with maximized
		options.addArguments("--start-maximized");
		// Initialize Chrome driver
		driver = new ChromeDriver(options);
		// Set default driver wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void testValidateAddtoCartFunctionality() throws Exception {
		// Get Test Data from Configuration Properties File
		String url = pros.getProperties("Url");
		String searchParameter = pros.getProperties("searchParameter");
		String sortOption = pros.getProperties("sortOption");
		String newXpathSort = "//div[text()='" + sortOption + "']";
		// Init HomePage Page
		HomePage homePage = new HomePage(driver);

		// Open URL
		homePage.openURL(url);

		// Check if Login Popup is display then close
		homePage.checkAndCloseLoginPopup();

		// Input Search Parameter to search box and select Search button
		homePage.searchProduct(searchParameter);

		// Update Sort Option Element and Click Sort Option
		WebElement elementNeedUpdate = homePage.getTxtSortOption();
		homePage.updateSortLocator(elementNeedUpdate, newXpathSort);

		// Validate the prices for all the products displayed till Page (2) are in
		// ascending order
		homePage.validateThePricesSortAscending();

	}

	@Test(priority = 2)
	public void testValidateSortFunctionality() throws Exception {
		// Get Test Data from Configuration Properties File
		String url = pros.getProperties("Url");
		String searchParameter = pros.getProperties("searchParameter");
		String sortOption = pros.getProperties("sortOption");
		String newXpathSort = "//div[text()='" + sortOption + "']";
		// Init HomePage Page
		HomePage homePage = new HomePage(driver);

		// Open URL
		homePage.openURL(url);

		// Check if Login Popup is display then close
		homePage.checkAndCloseLoginPopup();

		// Input Search Parameter to search box and select Search button
		homePage.searchProduct(searchParameter);

		// Update Sort Option Element and Click Sort Option
		WebElement elementNeedUpdate = homePage.getTxtSortOption();
		homePage.updateSortLocator(elementNeedUpdate, newXpathSort);

		// Verify Add To Cart Function and return totalAmount of all product added
		String totalAmount = homePage.addToCartFunction();

		// Go to Cart and Validate Cart with correct Total Amount
		homePage.validateCart(totalAmount);
	}

	@AfterMethod
	public void tearDown() {
		// Reset driver wait
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		// Quit driver
		driver.quit();
	}

}