package pageObject;

<<<<<<< HEAD
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

	private static WebDriver driver;
=======
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
	protected WebDriverWait wait;

	// Constructor BasePage
	public BasePage(WebDriver driver) {
<<<<<<< HEAD
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
=======
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

>>>>>>> 7a587271aafdc7c82cb77f140d5dee925d26b514
}
