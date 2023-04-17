package pageObject;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ExtentTestManager;

import static org.testng.Assert.assertEquals;

public class AddToCartPage extends BasePage{

    public AddToCartPage(WebDriver driver) {
        super(driver);
    }

    // Define All Elements
    @FindBy(xpath = "(//div[contains(@class,'_1AtVbE')]//a[text()])[2]")
    private WebElement nameProduct1;

    @FindBy(xpath = "(//div[contains(@class,'_1AtVbE')]//a[text()])[1]")
    private WebElement nameProduct2;
    @FindBy(xpath = "//div[text()='Total Amount']//following::div//span[contains(text(),'₹')]")
    private WebElement totalAmount;

    @FindBy(xpath = "(//span[contains(text(),'₹')][2])[2]")
    private WebElement priceProduct1;

    @FindBy(xpath = "(//span[contains(text(),'₹')][2])[1]")
    private WebElement priceProduct2;

    public void validateTotalAmount(String totalAmountExpected) {
        // Wait until Total Amount is Cart screen display
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        // Get Total Amount
        String totalAmountActual = totalAmount.getText().replaceAll("₹", "");
        // Compare with total Amount of 2 products added
        assertEquals(totalAmountExpected, totalAmountActual);
        ExtentTestManager.logMessage(Status.PASS, "Validate Total Amount in Cart Screen Successfully");
    }

    // Validate Name of Second Product
    public void validateNameSecondProduct(String name){
        // Wait until Product is visible
        wait.until(ExpectedConditions.visibilityOf(nameProduct1));

        // Get Name of Second Product
        String nameSecondProduct = nameProduct1.getText().replaceAll("₹", "");
        //Validate Name of Second Product
        assertEquals(nameSecondProduct, name);
        ExtentTestManager.logMessage(Status.PASS, "Validate Name of Product ( No2 ) Added in Cart Screen Successfully");
    }

    // Validate Name of Third Product
    public void validateNameThirdProduct(String name){
        // Wait until Product is visible
        wait.until(ExpectedConditions.visibilityOf(nameProduct2));

        // Get Name of Second Product
        String nameThirdProduct = nameProduct2.getText().replaceAll("₹", "");
        //Validate Name of Second Product
        assertEquals(nameThirdProduct, name);
        ExtentTestManager.logMessage(Status.PASS, "Validate Name of Product ( No3 ) Added in Cart Screen Successfully");
    }

    // Validate Price of Second Product
    public void validatePriceSecondProduct(String price){
        // Wait until Product is visible
        wait.until(ExpectedConditions.visibilityOf(priceProduct1));

        // Get Name of Second Product
        String priceSecondProduct = priceProduct1.getText().replaceAll("₹", "");
        //Validate Name of Second Product
        assertEquals(priceSecondProduct, price);
        ExtentTestManager.logMessage(Status.PASS, "Validate Price of Product ( No2 ) Added in Cart Screen Successfully");
    }

    // Validate Price of Third Product
    public void validatePriceThirdProduct(String price){
        // Wait until Product is visible
        wait.until(ExpectedConditions.visibilityOf(priceProduct2));

        // Get Name of Second Product
        String priceThirdProduct = priceProduct2.getText().replaceAll("₹", "");
        //Validate Name of Second Product
        assertEquals(priceThirdProduct, price);
        ExtentTestManager.logMessage(Status.PASS, "Validate Price of Product ( No3 ) Added in Cart Screen Successfully");
    }
}
