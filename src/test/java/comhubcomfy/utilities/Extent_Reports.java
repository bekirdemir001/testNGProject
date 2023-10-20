package comhubcomfy.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Extent_Reports {
    public static String message;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest
    public static void startExtentReports(String testName, String description, String userStory ) {

            //Report Path
            String currentDate = new SimpleDateFormat("MMddyyyy_hhmm").format(new Date());
            String path = System.getProperty("user.dir") + "/reports/HTML_Reports/" + userStory + "_" + currentDate + ".html";

            //Creating HTML report in the path
            extentHtmlReporter = new ExtentHtmlReporter(path);

            //Creating extent reports object for generating the entire reports with configuration
            extentReports = new ExtentReports();

            //Add report to the project
            extentReports.attachReporter(extentHtmlReporter);

            //Report Configuration
            extentHtmlReporter.config().setDocumentTitle("TestNG_Project Extent Reports");
            extentHtmlReporter.config().setReportName("Regression Test Result");
            extentHtmlReporter.config().setTheme(Theme.DARK);

            //Customizing The Report
            extentReports.setSystemInfo("Project", "TestNG_Project");
            extentReports.setSystemInfo("Test Environment", "Regression");
            extentReports.setSystemInfo("Team", "Test Team");
            extentReports.setSystemInfo("User Story", testName);
            extentReports.setSystemInfo("QA", "Bekir Demir");

    }

    //It runs when test pass
    public static void extentTestPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }
//
    //It runs when test fail
    public static void extentTestFail(String message) {
        if (extentTest != null) {
            extentTest.fail(message);
        }
    }

    public static void extentTestInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }


    //Create Extent Test object for logging
    public static void createExtentTest(String testName, String testDesc) {
        extentTest = extentReports.createTest(testName, testDesc);
    }

    @AfterMethod
    public static void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL,result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getTestName());
        }
        else {
            extentTest.log(Status.SKIP, result.getTestName());
        }
    }

    @AfterTest
    public static void tearDown() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

}
