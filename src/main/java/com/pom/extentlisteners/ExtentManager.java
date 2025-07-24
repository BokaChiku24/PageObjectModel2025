package com.pom.extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pom.base.Page;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ExtentManager {
	private static ExtentReports extent;
	public static String fileName;
	public static String screenshotName;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/surefire-reports/html/" + fileName);

		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Kunal Chavan");
		extent.setSystemInfo("Organization", "Selenium Data Driven");
		extent.setSystemInfo("Build no", "W2A-1234");

		return extent;
	}


	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		File screeshot = ((TakesScreenshot)  Page.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screeshot, new File(System.getProperty("user.dir") + "/reports/" + fileName));
	}

	public static void captureElementScreenshot(WebElement element) throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

		File screeshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screeshot, new File(System.getProperty("user.dir") + "/screenshot/"+ "Element_"+ fileName));
	}

}
