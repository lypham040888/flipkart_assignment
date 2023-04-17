package utils;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pageObject.BasePage;

import static utils.ExtentManager.extentReports;

public class ReportListener implements ITestListener {

    WebDriver driver;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        driver = BasePage.getDriver();
        LogUtils.info("Start testing " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LogUtils.info("End testing " + iTestContext.getName());
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info(getTestName(iTestResult) + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info(getTestName(iTestResult) + " test is passed.");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.logMessage(Status.PASS, "Test Case: "+iTestResult.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error(getTestName(iTestResult) + " test is failed.");

        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));

        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL,"Test Case: "+iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn(getTestName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

}