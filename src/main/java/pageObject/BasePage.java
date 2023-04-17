package pageObject;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

	private static WebDriver driver;
	protected WebDriverWait wait;

	// Constructor BasePage
	public BasePage(WebDriver driver) {
		BasePage.setDriver(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}
}
