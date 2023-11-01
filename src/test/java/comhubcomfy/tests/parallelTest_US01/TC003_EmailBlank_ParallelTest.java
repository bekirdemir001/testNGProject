package comhubcomfy.tests.parallelTest_US01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.HomePage;
import comhubcomfy.pages.RegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC003_EmailBlank_ParallelTest {
    private final String testName = "US01 || TC003-User leaves email blank";
    private final String expectedResult = "User cannot register and show warning message";
    String actualResult = "User did not register and showed warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
    public void emailBlank(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(ConfigReader.getProperty("URL"));

        extentTest.pass("User goes to 'Hubcomfy.com'");
        Faker faker = new Faker();

        HomePage homePage = new HomePage();
        homePage.registerButton.click();
        extentTest.pass("User clicks on 'Register' button");

        RegisterPage registerPage = new RegisterPage();
        registerPage.usernameInputBox.sendKeys(faker.name().username());
        extentTest.pass("User enters valid username");

        extentTest.pass("*** User leaves email blank ***");

        registerPage.passwordInputBox.sendKeys(faker.internet().password());
        extentTest.pass("User enters valid password");
        registerPage.privacyPolicy.click();
        extentTest.pass("User clicks on privacy policy");
        registerPage.signUpButton.click();
        extentTest.pass("User clicks on 'Sign Up' button");

        try {
            Assert.assertTrue(registerPage.userRegisterPage.isDisplayed());
            driver.close();
        }catch (Exception e){
            throw new RuntimeException();
        }finally {
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }
}