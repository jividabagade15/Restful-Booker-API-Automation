package Hooks;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.testng.PickleWrapper;
import utils.ExtentReportNG;

public class TestNGListener implements ITestListener{

	ExtentReports extent= ExtentReportNG.getReportObject();
	private static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail("Test Failed!");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped!");
	}

	@Override
	public void onTestStart(ITestResult result) {
		PickleWrapper wrapper=(PickleWrapper) result.getParameters()[0];
		String scenarioName= wrapper.getPickle().getName();
		ExtentTest extentTest= extent.createTest(scenarioName);
//		ExtentTest extentTest= extent.createTest(result.getMethod().getMethodName());
		
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed!");
	}

}
