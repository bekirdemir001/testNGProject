package comAlloverCommerce.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReusableMethods {
    public static String getScreenshot(String userStory) throws IOException {
        //Naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("MMddyyyy_hhmm").format(new Date());
        //TakesScreenshot is an interface of Selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        //Full path to the screenshot location
        String target = System.getProperty("user.dir") + "/reports/screenshots/" + userStory + "_" + date  + ".png";
        File destination = new File(target);
        //Save the screenshot to the path given
        FileUtils.copyFile(source, destination);
        return target;
    }

    public static String base64Screenshot(){
        String base64Screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    //Switching Window
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String w : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(w);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    public static void switchToWindow(int windowNumber) {
        List<String> list = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(list.get(windowNumber));
    }

    public static void jsClick(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement elementName = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        js.executeScript("arguments[0].click();", elementName);
    }

    public static void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement elementName = wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].click();", elementName);
    }

    //HARD WAIT:
    //@param : second
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Explicit Wait
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickAbility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickAbility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}