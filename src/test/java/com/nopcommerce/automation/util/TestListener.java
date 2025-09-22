package com.nopcommerce.automation.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getReporter();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();

        // Log failure with class and method
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
        Throwable throwable = result.getThrowable();

        test.fail("❌ Test Failed");
        test.fail("Class: " + className);
        test.fail("Method: " + methodName);
        test.fail("Exception: " + throwable);

        // Capture and attach screenshot
        String screenshotPath = ScreenshotUtil.captureScreenshot(methodName);
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, methodName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Getter for logging steps inside test methods
    public static ExtentTest getTest() {
        return testThread.get();
    }
}
