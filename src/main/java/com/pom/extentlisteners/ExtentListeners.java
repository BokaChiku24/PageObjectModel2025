package com.pom.extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.pom.base.Page;
import com.pom.utilities.Utilities;
import org.testng.*;

import java.io.IOException;
import java.util.Date;

public class ExtentListeners extends Page implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager
			.createInstance(fileName);

	public static ExtentTest test;
	public void onTestStart(ITestResult result) {
		Reporter.log("Test case start: " + result.getMethod().getMethodName());
		test = extent
				.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
		if(!Utilities.isTestRunnable(result.getName(),excel)) {
			throw new SkipException("Skipping the test" + result.getName().toUpperCase()
					+ " as run mode is set to No");
		}
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
		Reporter.log("Test case finished: " + result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		//try {
		//	Utilities.captureScreenshot();
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		System.setProperty("org.uncommons.reportng.escape-output", "false"); // To generate hyperlink under ReportNG
		Reporter.log("Captuing screenshot..");
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		Reporter.log("Screenshot captured..");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src =" + Utilities.screenshotName
				+ " height=200 width=200></img></a>");
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " FAILED" + "</b>";

		test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",
				MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		//test.log(Status.FAIL, m);
		test.log(Status.FAIL,
				result.getName().toUpperCase() + " : Fail" + " : Exception is - " + result.getThrowable()+m);



	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub

	}

}
