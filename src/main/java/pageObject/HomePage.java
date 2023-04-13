package pageObject;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

	@FindBy(xpath = "//div[@data-id][2]")
	private WebElement productNo2;

	@FindBy(xpath = "//div[@data-id][3]")
	private WebElement productNo3;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement btnAddToCart;

	@FindBy(xpath = "//span[text()='Cart']")
	private WebElement cartIcon;

	@FindBy(xpath = "//h1//span")
	private WebElement productName;

	@FindBy(xpath = "(//div[contains(text(),'₹')])[1]")
	private WebElement productPrice;

	@FindBy(xpath = "//div[text()='Total Amount']//following::div//span[contains(text(),'₹')]")
	private WebElement totalAmount;

	@FindAll({ @FindBy(xpath = "//div[@data-id]/following::div[contains(text(),'₹')][1]") })
	List<WebElement> myListPrices;

	// Open URL Function
	public void openURL(String url) {

		// Open URL
		driver.get(url);
		// Change to maximize window
		driver.manage().window().maximize();
	}

	public void searchProduct(String product) {

		// Input product to Search box
		txtSearchTextBox.sendKeys(product);
		// Click Search button
		btnSearchButton.click();
		// Wait until Sort Option is visible
		wait.until(ExpectedConditions.visibilityOf(txtSortOption));
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
	}

	public WebElement getTxtSortOption() {
		return txtSortOption;
	}

	public void updateSortLocator(WebElement element, String newLocator) throws Exception {

		// Wait until Default SortOption is visible
		wait.until(ExpectedConditions.visibilityOf(element));

		// Update new xpath for Element
		element = driver.findElement(By.xpath(newLocator));

		// Click Element
		element.click();

		// Wait some second
		Thread.sleep(2000);
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
				break;
			}
		}

		// Wait until Add To Cart Button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(btnAddToCart));

		// Click Add To Cart button
		btnAddToCart.click();

		// Get product name
		String productNo2Name = productName.getText();

		// Get product price
		String productNo2Price = productPrice.getText().replaceAll("₹", "");

		// Wait some second
		Thread.sleep(2000);

		// Close current window handle
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
		System.out.println("List is sorted: " + isSorted);
	}

}
