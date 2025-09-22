package com.nopcommerce.automation.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/test-output/extent-report.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "Automation Framework");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Team QA");
        }
        return extent;
    }
}
