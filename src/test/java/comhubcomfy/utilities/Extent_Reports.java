package comhubcomfy.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;


public class Extent_Reports {
    public static String message;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static ExtentHtmlReporter extentHtmlReporter;

    public static void createExtentReports() {
        if (extentReports == null) {
            String reportName = "us13_html_report.html";
            extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/reports/HTML_Reports" + reportName);
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentHtmlReporter);

            // HTML raporu yapılandırılır
            extentHtmlReporter.config().setDocumentTitle("TestNG Extent Reports");
            extentHtmlReporter.config().setReportName("Regression Test Result");

            // Raporlama bilgileri girilir
            extentReports.setSystemInfo("Project", "TestNG_Project");
            extentReports.setSystemInfo("Test Environment", "Regression");
            extentReports.setSystemInfo("Team", "Test Team");
            extentReports.setSystemInfo("User Story", "US13 | Vendor Shipping Adress (Teslimat Adresi)  ekleyebilmeli");
            extentReports.setSystemInfo("QA", "Bekir Demir");

            // Ekran görüntüleri için klasör oluşturulur
            File ekranGoruntuleriKlasoru = new File(System.getProperty("user.dir") + "/src/test/java/reports/screenshots");
            ekranGoruntuleriKlasoru.mkdir();
        }
    }

    public static void extentTestPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }

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
    public static void createExtentTest(String testName, String testDesc) {
        extentTest = extentReports.createTest(testName, testDesc);
    }

    public static void finishExtentReports() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
