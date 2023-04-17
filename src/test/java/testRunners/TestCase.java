package testRunners;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import dataProvider.ConfigPropertiesFile;
import pageObject.ProductDetailPage;
import pageObject.HomePage;
import utils.ReportListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Listeners(ReportListener.class)
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 2)
	public void testValidateAddtoCartFunctionality() throws Exception {
		// Get Test Data from Configuration Properties File
		String url = pros.getProperties("Url");
		String searchParameter = pros.getProperties("searchParameter");
		String sortOption = pros.getProperties("sortOption");
		String newXpathSort = "//div[text()='" + sortOption + "']";
		// Initial HomePage Page
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

	@Test(priority = 1)
	public void testValidateSortFunctionality() throws Exception {
		// Get Test Data from Configuration Properties File
		String url = pros.getProperties("Url");
		String searchParameter = pros.getProperties("searchParameter");
		String sortOption = pros.getProperties("sortOption");
		String newXpathSort = "//div[text()='" + sortOption + "']";
		// Initial HomePage Page
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

		// Verify Add To Cart Second Product, get Name and Price of this Product
		WebElement secondProduct = homePage.getProductNo2();
		String nameAndPriceSecondProduct = homePage.addToCartProduct(secondProduct);
		String productSecondName = nameAndPriceSecondProduct.split(":")[0];
		String productSecondPrice = nameAndPriceSecondProduct.split(":")[1];

		// Verify Add To Cart 3rd Product, get Name and Price of this Product
		WebElement thirdProduct = homePage.getProductNo3();
		String nameAndPriceThirdProduct = homePage.addToCartProduct(thirdProduct);
		String thirdProductName = nameAndPriceThirdProduct.split(":")[0];
		String thirdProductPrice = nameAndPriceThirdProduct.split(":")[1];

		// Get Total Amount of Product add to card
		int sum = Integer.parseInt(productSecondPrice) + Integer.parseInt(thirdProductPrice);
		String totalAmount = Integer.toString(sum);

		// Go to Cart Screen
		homePage.goToCartScreen();

		// Init Add To Cart Page
		ProductDetailPage addToCartPage = new ProductDetailPage(driver);

		// Validate Name of Second Product
		addToCartPage.validateNameSecondProduct(productSecondName);

		// Validate Name of Third Product
		addToCartPage.validateNameThirdProduct(thirdProductName);

		// Validate Price of Second Product
		addToCartPage.validatePriceSecondProduct(productSecondPrice);

		// Validate Price of Third Product
		addToCartPage.validatePriceThirdProduct(thirdProductPrice);

		// Validate Cart with correct Total Amount
		addToCartPage.validateTotalAmount(totalAmount);
	}

	@AfterMethod
	public void tearDown() {
		// Reset driver wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		// Quit driver
		driver.quit();
	}

}