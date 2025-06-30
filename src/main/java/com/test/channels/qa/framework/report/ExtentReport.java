package com.test.channels.qa.framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.NoArgsConstructor;
import org.testng.ITestResult;

/**
 * Test report using Extent library. This is potentially not needed. A better approach
 * is to plug it in pom.xml, a good article is here:
 * <a href="https://safebear.co.uk/extentreports">
 * https://safebear.co.uk/extentreports
 * </a>
 * @author abhishekjain
 *
 */
@NoArgsConstructor
public class ExtentReport {

    private ExtentReports report;

    /**
     * Initialize a report
     * @param filePath - the path to the report directory
     * @param platform - the current mobile platform
     * @param environment - the current environment .e.g QA
     */
//    public ExtentReport(String filePath, String platform, String environment) {
//        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
//        report = new ExtentReports();
//        report.attachReporter(htmlReporter);
//
//        report.setSystemInfo("OS", platform);
//        report.setSystemInfo("Environment", environment);
//
//        htmlReporter.config().setChartVisibilityOnOpen(true);
//        String title =
//                new StringBuilder("Automation Report - ")
//                .append(DateUtil.getNow())
//                .append(" - ")
//                .append(platform)
//                .append(" - ")
//                .append(environment)
//                .toString();
//        htmlReporter.config().setDocumentTitle(title);
//        htmlReporter.config().setReportName(title);
//        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
//        htmlReporter.config().setTheme(Theme.DARK);
//    }

    /**
     * Log test results
     * @param result - the current test results
     */
    public void log(ITestResult result) {
        ExtentTest test = report.createTest(result.getTestName());
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(
                    Status.FAIL,
                    MarkupHelper.createLabel(
                            result.getName() + " Test case FAILED due to the issues below:", ExtentColor.RED));
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(
                    Status.PASS,
                    MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else {
            test.log(
                    Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    /**
     * Flush the report
     */
    public void finish() {
        report.flush();
    }

}
