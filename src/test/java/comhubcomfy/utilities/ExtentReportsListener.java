package comhubcomfy.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ExtentReportsListener implements ITestListener {
    //Extent Report is created, when test starts
    @Override
    public void onStart(ITestContext context) {
        Extent_Reports.createExtentReports(context.getName());
    }

    //testName and description are added, when test starts
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName();
        String testDescription = result.getMethod().getDescription();
        Extent_Reports.createExtentTest(testName, testDescription);
    }

    //Test successful message and mark are added, when test passes
    @Override
    public void onTestSuccess(ITestResult result) {
        String passMark = "&#9989";
        Extent_Reports.extentTestPass("<span style='color:green; font-weight:bold'>TEST PASSED  </span> " + passMark);
        Extent_Reports.extentTestPass(Extent_Reports.message);
    }

    //Test fail message and mark are added and screenshot is taken, when test fails
    @Override
    public void onTestFailure(ITestResult result) {
        //Adding fail message
        String failMark = "&#10060";
        Extent_Reports.extentTestFail("<span style='color:red; font-weight:bold'>Test Failed!  </span> " + failMark);
        Extent_Reports.extentTestFail(Extent_Reports.message);
    }

    //Extent Report is closed, when test finishes
    @Override
    public void onFinish(ITestContext context) {
        Extent_Reports.tearDown();
    }
}