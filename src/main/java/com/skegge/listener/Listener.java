package com.skegge.listener;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;
import com.skegge.base.TestBase;

public class Listener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		System.out.println("Test Case Execution Started : " + result.getName());
		

	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Case Execution Successed : " + result.getName());
		closeApplication();
		
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Case Execution Skipped : " + result.getName());
		closeApplication();
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Test Case Execution Failed : " + result.getName());
		takeScreenshotFailedTC(driver,result.getName());
		closeApplication();


	}


	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

}
