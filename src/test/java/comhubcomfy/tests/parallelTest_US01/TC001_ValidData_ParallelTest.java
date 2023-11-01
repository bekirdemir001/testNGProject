package comhubcomfy.tests.parallelTest_US01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.HomePage;
import comhubcomfy.pages.MyAccountPage;
import comhubcomfy.pages.RegisterPage;
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

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC001_ValidData_ParallelTest {
    private final String testName = "US01 || TC001-User enters valid data";
    private final String expectedResult = "User registered successfully and showed 'Sign Out'";
    String actualResult = "User registered successfully and showed 'Sign Out'";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
    public void validData() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://hubcomfy.com/");

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

        WebElement passwordInputBox = driver.findElement(By.id("reg_password"));
        passwordInputBox.sendKeys(faker.internet().password());
        Extent_Reports.extentTestPass("User enters valid password");

        WebElement privacyPolicy = driver.findElement(By.id("register-policy"));
        privacyPolicy.click();
        Extent_Reports.extentTestPass("User clicks on privacy policy");

        WebElement signUpButton = driver.findElement(By.name("register"));
        signUpButton.click();
        Extent_Reports.extentTestPass("User clicks on 'Sign Up' button");

        WebElement signOutButton = driver.findElement(By.xpath("//a[@href='https://hubcomfy.com/my-account-2/customer-logout/']"));

        try {
            Assert.assertTrue(signOutButton.isEnabled());
            driver.close();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }
}