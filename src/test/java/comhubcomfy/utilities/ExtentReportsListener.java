package comhubcomfy.utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        //Taking screenshot
        File goruntu = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        String currentDate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/src/test/java/reports/screenshots/" + currentDate + "image.png";
        File file = new File(path);
        try {
            // Ekran görüntüsünü dosyaya kaydetme
            FileUtils.copyFile(goruntu, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Adding fail message
        String failMark = "&#10060";
        Extent_Reports.extentTestFail("<span style='color:red; font-weight:bold'>Test Failed!  </span> " + failMark);
        Extent_Reports.extentTestFail(Extent_Reports.message);


        // Raporlama
       //extentTest.log(Status.FAIL, "Test failed");
       //try {
       //    extentTest.addScreenCaptureFromPath(path);
       //} catch (IOException e) {
       //    e.printStackTrace();
       //} finally {
       //    Driver.closeDriver();
       //}
        try {
            //Extent_Reports.extentTest.fail("<span style='color:green; font-weight:bold; font-size: 16px'>EKRAN GÖRÜNTÜSÜ</span><br>(Resmi büyütmek için üzerine tıklayınız.)", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Extent_Reports.extentTest.addScreenCaptureFromPath(path + ".png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            Driver.closeDriver();
        }


    }

    //Extent Report is closed, when test finishes
    @Override
    public void onFinish(ITestContext context) {
        Extent_Reports.tearDown();
    }
}