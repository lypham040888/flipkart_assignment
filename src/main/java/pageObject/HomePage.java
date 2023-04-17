package pageObject;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import utils.ExtentTestManager;
import utils.LogUtils;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Define All Elements
	@FindBy(xpath = "//button[text()='✕']")
	private WebElement btnCloseLoginPopup;

	@FindBy(name = "q")
	private WebElement txtSearchTextBox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSearchButton;

	@FindBy(xpath = "//div[text()='Relevance']")
	private WebElement txtSortOption;

	@FindBy(xpath = "//button[text()='Request OTP']")
	private WebElement btnRequestOTP;

	@FindBy(xpath = "(//div[@data-id])[2]")
	private WebElement productNo2;

	@FindBy(xpath = "(//div[@data-id])[3]")
	private WebElement productNo3;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement btnAddToCart;

	@FindBy(xpath = "//span[text()='Cart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//h1//span")
	private WebElement atcProductName;

	@FindBy(xpath = "(//div[contains(text(),'₹')])[1]")
	private WebElement atcProductPrice;

	@FindAll({ @FindBy(xpath = "//div[@data-id]/following::div[contains(text(),'₹')][1]") })
	List<WebElement> myListPrices;

	// Open URL Function
	public void openURL(String url) {

		// Open URL
		getDriver().get(url);
		// Change to maximize window
		getDriver().manage().window().maximize();
		ExtentTestManager.logMessage(Status.PASS, "Open Browser and Go to " + url + " Successfully");
	}

	public void searchProduct(String product) {

		// Input product to Search box
		txtSearchTextBox.sendKeys(product);
		// Click Search button
		btnSearchButton.click();
		// Wait until Sort Option is visible
		wait.until(ExpectedConditions.visibilityOf(txtSortOption));
		ExtentTestManager.logMessage(Status.PASS, "Search Products Successfully");
	}

	public boolean isLoginPopupDisplayed() {
		try {
			// Wait until Login Popup open
			wait.until(ExpectedConditions.visibilityOf(btnRequestOTP));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void checkAndCloseLoginPopup() {
		boolean isDisplay = isLoginPopupDisplayed();
		if (isDisplay) {
			// If Login Popup is opening --> Close
			btnCloseLoginPopup.click();
		}
		ExtentTestManager.logMessage(Status.PASS, "Close Login Popup Successfully");
	}

	public WebElement getTxtSortOption() {
		return txtSortOption;
	}

	public void updateSortLocator(WebElement element, String newLocator) throws Exception {

		// Wait until Default SortOption is visible
		wait.until(ExpectedConditions.visibilityOf(element));

		// Update new xpath for Element
		element = getDriver().findElement(By.xpath(newLocator));

		// Click Element
		element.click();

		// Wait some second
		Thread.sleep(2000);
		ExtentTestManager.logMessage(Status.PASS, "Update Sort Option Successfully");
	}

	public String addToCartProduct(WebElement element) throws Exception {

		String result = "";
		// Get current window handle
		String currentWD = getDriver().getWindowHandle();

		// Click Product
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();

		for (String windowHandle : getDriver().getWindowHandles()) {
			if (!windowHandle.equals(currentWD)) {

				// Switch to new window handle
				getDriver().switchTo().window(windowHandle);
				break;
			}
		}

		// Wait until Add To Cart Button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));

		// Click Add To Cart button
		btnAddToCart.click();

		// Get product name
		String productName = atcProductName.getText().split("\\(")[0].trim();

		// Get product price
		String productPrice = atcProductPrice.getText().replaceAll("₹", "");

		// Wait some second
		Thread.sleep(2000);

		// Close current window handle
		getDriver().close();

		// Switch to original window handle
		getDriver().switchTo().window(currentWD);

		result = productName + ":" + productPrice;
		ExtentTestManager.logMessage(Status.PASS, "Add Products To Cart Successfully");
		return result;
	}

	public void validateThePricesSortAscending() {

		List<WebElement> elements = myListPrices;

		// Create List to store elements price
		List<Integer> values = new ArrayList<>();

		// Add Prices to List
		for (WebElement element : elements) {
			String text = element.getText().replaceAll("₹", "");
			int value = Integer.parseInt(text);
			values.add(value);
		}

		// Check prices are ascending
		List<Integer> sortedValues = new ArrayList<>(values);
		// Sort list
		Collections.sort(sortedValues);
		// Compare 2 list an get boolean
		boolean isSorted = sortedValues.equals(values);
		// Verify boolean is true
		assertEquals(isSorted, true);
		ExtentTestManager.logMessage(Status.PASS,
				"Validate the prices for all the products displayed till Page (2) are in ascending order");
	}

	public void goToCartScreen() throws InterruptedException {
		// Wait until Cart Icon clickable
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
		// Click to Cart Icon
		cartIcon.click();
		Thread.sleep(2000);
		ExtentTestManager.logMessage(Status.PASS, "Go To Cart Successfully");
	}

	public WebElement getProductNo2() {
		return productNo2;
	}

	public WebElement getProductNo3() {
		return productNo3;
	}

}
