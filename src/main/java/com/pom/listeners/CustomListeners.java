package com.pom.listeners;

import com.aventstack.extentreports.Status;
import com.pom.base.Page;
import com.pom.utilities.Utilities;
import org.testng.*;

import java.io.IOException;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	public static String messageBody;

	public void onTestStart(ITestResult result) {
		// not implemented
		Reporter.log("Test case start: " + result.getMethod().getMethodName());
		//test = report.createTest(result.getName().toUpperCase());


		  // Run modes - Y/N
		if(!Utilities.isTestRunnable(result.getName(),excel)) {
		  throw new SkipException("Skipping the test" + result.getName().toUpperCase()
		  + " as run mode is set to No");
		}


	}

	public void onTestSuccess(ITestResult result) {
		// not implemented
		Reporter.log("Test case finished: " + result.getMethod().getMethodName());
		test.log(Status.PASS, result.getName().toUpperCase() + " : Pass");
		//report.flush();

	}

	public void onTestFailure(ITestResult result) {
		// not implemented
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
		test.log(Status.FAIL,
				result.getName().toUpperCase() + " : Fail" + " : Exception is - " + result.getThrowable());
		//test.log(Status.FAIL, "Screenshot is: " + test.captureScreenshot(Utilities.screenshotName));
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
		test.log(Status.SKIP, result.getName().toUpperCase() + " Skipped the test case");
		//report.flush();
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		// not implemented
	}

	public void onStart(ISuite suite) {
		// not implemented
	}


	public void onFinish(ISuite suite) {
		//report.flush();
		// not implemented
		/*
		MonitoringMail mail = new MonitoringMail();

		try {
			messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
					+ ":8080/job/DataDrivenLiveProject/Extent_Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 */
	}
}