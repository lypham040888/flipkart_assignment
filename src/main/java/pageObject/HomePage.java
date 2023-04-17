package pageObject;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

<<<<<<< HEAD
import com.aventstack.extentreports.Status;
=======
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
<<<<<<< HEAD
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import utils.ExtentTestManager;
import utils.LogUtils;
=======
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514

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

<<<<<<< HEAD
	@FindBy(xpath = "(//div[@data-id])[2]")
	private WebElement productNo2;

	@FindBy(xpath = "(//div[@data-id])[3]")
=======
	@FindBy(xpath = "//div[@data-id][2]")
	private WebElement productNo2;

	@FindBy(xpath = "//div[@data-id][3]")
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
	private WebElement productNo3;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement btnAddToCart;

	@FindBy(xpath = "//span[text()='Cart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//h1//span")
<<<<<<< HEAD
	private WebElement atcProductName;

	@FindBy(xpath = "(//div[contains(text(),'₹')])[1]")
	private WebElement atcProductPrice;
=======
	private WebElement productName;

	@FindBy(xpath = "(//div[contains(text(),'₹')])[1]")
	private WebElement productPrice;

	@FindBy(xpath = "//div[text()='Total Amount']//following::div//span[contains(text(),'₹')]")
	private WebElement totalAmount;
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514

	@FindAll({ @FindBy(xpath = "//div[@data-id]/following::div[contains(text(),'₹')][1]") })
	List<WebElement> myListPrices;

	// Open URL Function
	public void openURL(String url) {

		// Open URL
<<<<<<< HEAD
		getDriver().get(url);
		// Change to maximize window
		getDriver().manage().window().maximize();
		ExtentTestManager.logMessage(Status.PASS, "Open Browser and Go to " + url + " Successfully");
=======
		driver.get(url);
		// Change to maximize window
		driver.manage().window().maximize();
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
	}

	public void searchProduct(String product) {

		// Input product to Search box
		txtSearchTextBox.sendKeys(product);
		// Click Search button
		btnSearchButton.click();
		// Wait until Sort Option is visible
		wait.until(ExpectedConditions.visibilityOf(txtSortOption));
<<<<<<< HEAD
		ExtentTestManager.logMessage(Status.PASS, "Search Products Successfully");
=======
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
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
<<<<<<< HEAD
		ExtentTestManager.logMessage(Status.PASS, "Close Login Popup Successfully");
=======
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
	}

	public WebElement getTxtSortOption() {
		return txtSortOption;
	}

	public void updateSortLocator(WebElement element, String newLocator) throws Exception {

		// Wait until Default SortOption is visible
		wait.until(ExpectedConditions.visibilityOf(element));

		// Update new xpath for Element
<<<<<<< HEAD
		element = getDriver().findElement(By.xpath(newLocator));
=======
		element = driver.findElement(By.xpath(newLocator));
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514

		// Click Element
		element.click();

		// Wait some second
		Thread.sleep(2000);
<<<<<<< HEAD
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
=======
	}

	public String addToCartFunction() throws Exception {

		// Get current window handle
		String currentWD = driver.getWindowHandle();

		// Click Second Product
		productNo2.click();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(currentWD)) {

				// Switch to new window handle
				driver.switchTo().window(windowHandle);
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
				break;
			}
		}

		// Wait until Add To Cart Button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));

		// Click Add To Cart button
		btnAddToCart.click();

		// Get product name
<<<<<<< HEAD
		String productName = atcProductName.getText().split("\\(")[0].trim();
		;

		// Get product price
		String productPrice = atcProductPrice.getText().replaceAll("₹", "");
=======
		String productNo2Name = productName.getText();

		// Get product price
		String productNo2Price = productPrice.getText().replaceAll("₹", "");
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514

		// Wait some second
		Thread.sleep(2000);

		// Close current window handle
<<<<<<< HEAD
		getDriver().close();

		// Switch to original window handle
		getDriver().switchTo().window(currentWD);

		result = productName + ":" + productPrice;
		ExtentTestManager.logMessage(Status.PASS, "Add Products To Cart Successfully");
		return result;
=======
		driver.close();

		// Switch to original window handle
		driver.switchTo().window(currentWD);

		// Click third product
		productNo3.click();

		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(currentWD)) {

				// Switch to new window handle
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Wait until Add To Cart Button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));

		// Click Add To Cart button
		btnAddToCart.click();

		// Get product name
		String productNo3Name = productName.getText();

		// Get product price
		String productNo3Price = productPrice.getText().replaceAll("₹", "");

		// Wait some second
		Thread.sleep(2000);

		// Close current window handle
		driver.close();

		// Switch to original window handle
		driver.switchTo().window(currentWD);

		// Get Total Amount of 2 products added to cart
		int sum = Integer.parseInt(productNo2Price) + Integer.parseInt(productNo3Price);
		String totalAmount = "₹" + Integer.toString(sum);

		return totalAmount;
	}

	public void validateCart(String totalAmountExpected) {
		// Wait until Cart Icon clickable
		wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
		// Click to Cart Icon
		cartIcon.click();
		// Wait until Total Amount is Cart screen display
		wait.until(ExpectedConditions.visibilityOf(totalAmount));
		// Get Total Amount
		String totalAmountActual = totalAmount.getText();
		// Compare with total Amount of 2 products added
		assertEquals(totalAmountExpected, totalAmountActual);
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
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
<<<<<<< HEAD
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
=======
		System.out.println("List is sorted: " + isSorted);
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
	}

}
