package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static final ExtentReports extentReports = new ExtentReports();

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				"./Report/ExtentReport_" + System.currentTimeMillis() + ".html");
		reporter.config().setReportName("Automation Extent Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Project Information", "Selenium Java Framework");
		extentReports.setSystemInfo("Author", "Freelancer");
		return extentReports;
	}
}