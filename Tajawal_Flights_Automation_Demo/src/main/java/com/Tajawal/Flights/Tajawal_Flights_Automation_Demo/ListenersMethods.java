package com.Tajawal.Flights.Tajawal_Flights_Automation_Demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersMethods extends BaseFunctions implements ITestListener, ISuiteListener {

	public final static Logger LOG = LoggerFactory.getLogger(ListenersMethods.class);

	// When Test case get Started, this method is called.
	@Override
	public void onTestStart(ITestResult Result) {
		LOG.info(Result.getName() + " test case started");
	}

	// When Test case get passed, this method is called.
	@Override
	public void onTestSuccess(ITestResult Result) {

		LOG.info("Woohoo !!! Testcase {} passed ", Result.getName());
		// takeScreenshot(Result.getName(), true); //Uncomment
		// this line to take screenshots of passed testcases

	}

	// When Test case get failed, this method is called.
	@Override
	public void onTestFailure(ITestResult Result) {
		LOG.error("OOPS !!! Testcases {} failed", Result.getName());
		takeScreenshot(Result.getName(), false);

	}

	// When Test case get Skipped, this method is called.
	@Override
	public void onTestSkipped(ITestResult Result) {
		LOG.error("The name of the testcase Skipped is :" + Result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub

		LOG.info("Suite {} stating", suite.getName());

	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		driver.quit();
		LOG.info("Suite {} Finished", suite.getName());

	}

}
