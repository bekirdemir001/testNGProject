package comhubcomfy.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReusableMethods {
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getScreenshot(String userStory) throws IOException {
        //Naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("MMddyyyy_hhmm").format(new Date());
        //TakesScreenshot is an interface of Selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Full path to the screenshot location
        String target = System.getProperty("user.dir") + "/reports/screenshots/" + userStory + date  + ".png";
        File destination = new File(target);
        //Save the screenshot to the path given
        FileUtils.copyFile(source, destination);
        return target;
    }





}