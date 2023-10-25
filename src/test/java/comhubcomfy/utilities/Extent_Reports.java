package comhubcomfy.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Extent_Reports {
    public static String message;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static ExtentHtmlReporter extentHtmlReporter;

    @BeforeTest
    public static void createExtentReports(String userStory) {
        if (extentTest == null) {
            //Report Path
            String currentDate = new SimpleDateFormat("MMddyyyy_hhmm").format(new Date());
            String path = System.getProperty("user.dir") + "/reports/HTML_Reports/" + userStory + currentDate + ".html";

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
            extentReports.setSystemInfo("Application", "Hubcomfy.com");
            extentReports.setSystemInfo("Browser", "Chrome");
            extentReports.setSystemInfo("Team", "Test Team");
            extentReports.setSystemInfo("QA", "Bekir Demir");

            // Ekran görüntüleri için klasör oluşturulur
            File ekranGoruntuleriKlasoru = new File(System.getProperty("user.dir") + "/src/test/java/reports/screenshots");
            ekranGoruntuleriKlasoru.mkdir();
        }
    }
    //It runs when test pass
    public static void extentTestPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }


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


    public static void tearDown() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
