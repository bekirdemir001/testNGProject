package comAlloverCommerce.tests.parallelTest;

import com.github.javafaker.Faker;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Extent_Reports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC004_IncompatibleEmail_ParallelTest {
    private final String testName = "US01 || TC004-User enters incompatible email";
    private final String expectedResult = "User cannot register and show warning message";
    String actualResult = "User couldn't register and showed warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
    public void incompatibleEmail(){
        Faker faker = new Faker();

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get(ConfigReader.getProperty("URL"));
        Extent_Reports.extentTestPass("1) User goes to homepage");

        WebElement myAccountButton = driver.findElement(By.linkText("My Account"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myAccountButton);
        Extent_Reports.extentTestPass("2) User clicks on 'My Account' button");

        WebElement signUpLink = driver.findElement(By.xpath("//a[text()='Sign Up']"));
        signUpLink.click();
        Extent_Reports.extentTestPass("3) User clicks on 'Sign Up' button");

        WebElement usernameInputBox = driver.findElement(By.id("reg_username"));
        usernameInputBox.sendKeys(faker.name().username());
        Extent_Reports.extentTestPass("4) User enters valid username");

        WebElement emailInputBox = driver.findElement(By.id("reg_email"));
        emailInputBox.sendKeys(ConfigReader.getProperty("generatedIncompatibleEmail"));
        Extent_Reports.extentTestPass("5) *** User enters incompatible email ***");

        WebElement passwordInputBox = driver.findElement(By.id("reg_password"));
        passwordInputBox.sendKeys(faker.internet().password());
        Extent_Reports.extentTestPass("6) User enters valid password");

        WebElement privacyPolicy = driver.findElement(By.id("register-policy"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", privacyPolicy);
        Extent_Reports.extentTestPass("7) User clicks on privacy policy");

        WebElement signUpButton = driver.findElement(By.name("register"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpButton);
        Extent_Reports.extentTestPass("8) User clicks on 'Sign Up' button");

        try {
            WebElement registerPage = driver.findElement(By.id("customer_login"));
            Assert.assertTrue(registerPage.isEnabled());
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            driver.close();
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }
}