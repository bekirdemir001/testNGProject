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

public class TC009_PasswordStrong_ParallelTest {
    private final String testName = "US01 || TC009-User enters strong password";
    private final String expectedResult = "'Sign Up' button is clickable and user registers successfully";
    String actualResult = "User registered successfully and showed 'Sign Out', but didn't show warning message";


    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void passwordStrong(){

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

        WebElement passwordInputBox = driver.findElement(By.id("reg_password"));
        passwordInputBox.sendKeys(faker.internet().password());
        Extent_Reports.extentTestPass("*** User enters strong password ***");

        WebElement privacyPolicy = driver.findElement(By.id("register-policy"));
        privacyPolicy.click();
        Extent_Reports.extentTestPass("User clicks on privacy policy");

        WebElement signUpButton = driver.findElement(By.name("register"));
        signUpButton.click();
        Extent_Reports.extentTestPass("User clicks on 'Sign Up' button");

        try {
            WebElement signOutButton = driver.findElement(By.xpath("//a[@href='https://hubcomfy.com/my-account-2/customer-logout/']"));
            Assert.assertFalse(signOutButton.isEnabled());
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