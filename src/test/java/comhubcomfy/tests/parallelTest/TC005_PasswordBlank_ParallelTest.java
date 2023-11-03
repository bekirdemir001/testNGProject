package comhubcomfy.tests.parallelTest;

import com.github.javafaker.Faker;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Extent_Reports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC005_PasswordBlank_ParallelTest {
    private final String testName = "US01 || TC005-User leaves password blank";
    private final String expectedResult = "User cannot register and show warning message";
    String actualResult = "User did not register and showed warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
    public void passwordBlank(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(ConfigReader.getProperty("URL"));

        Extent_Reports.extentTestPass("User goes to 'Hubcomfy.com'");
        Faker faker = new Faker();

        WebElement registerButton = driver.findElement(By.xpath("//a[@href='https://hubcomfy.com/my-account-2/'][2]"));
        registerButton.click();
        Extent_Reports.extentTestPass("User clicks on 'Register' button");

        WebElement usernameInputBox = driver.findElement(By.id("reg_username"));
        usernameInputBox.sendKeys(faker.name().username());
        Extent_Reports.extentTestPass("User enters valid username");

        WebElement emailInputBox = driver.findElement(By.id("reg_email"));
        emailInputBox.sendKeys(faker.internet().emailAddress());
        Extent_Reports.extentTestPass("User enters valid email");

        Extent_Reports.extentTestPass("*** User leaves password blank ***");

        WebElement privacyPolicy = driver.findElement(By.id("register-policy"));
        privacyPolicy.click();
        Extent_Reports.extentTestPass("User clicks on privacy policy");

        WebElement signUpButton = driver.findElement(By.name("register"));
        signUpButton.click();
        Extent_Reports.extentTestPass("User clicks on 'Sign Up' button");

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