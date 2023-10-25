package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P02_RegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC001_ValidData {
    String testCaseID = "US01_TC001";
    private final String testName = "US01 || TC001-User enters valid data";
    private final String expectedResult = "User registered successfully and showed 'Sign Out'";
    String actualResult = "User registered successfully and showed 'Sign Out'";

   @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result:</span> " + expectedResult)
   public void successfulCustomerRegistration() throws IOException {

       Driver.getDriver().get(ConfigReader.getProperty("URL"));
       extentTest.pass("User goes to 'Hubcomfy.com'");
       Faker faker = new Faker();

       P01_HomePage homePage = new P01_HomePage();
       homePage.registerButton.click();
       extentTest.pass("User clicks on 'Register' button");

       P02_RegisterPage registerPage = new P02_RegisterPage();
       registerPage.usernameInputBox.sendKeys(faker.name().username());
       extentTest.pass("User enters valid username");
       registerPage.emailInputBox.sendKeys(faker.internet().emailAddress());
       extentTest.pass("User enters valid email");
       registerPage.passwordInputBox.sendKeys(faker.internet().password());
       extentTest.pass("User enters valid password");
       registerPage.privacyPolicy.click();
       extentTest.pass("User clicks on privacy policy");
       registerPage.signUpButton.click();
       extentTest.pass("User clicks on 'Sign Up' button");

       Assert.assertTrue(registerPage.signOutButton.isEnabled());
       ReusableMethods.getScreenshot(testCaseID);

       Extent_Reports.message =
               "<span style='color:green; font-weight:bold; font-size: 14px'>Test Result: </span>" +
                       "<br>" +
                       "<span style='color:purple; font-size: 16px'>" + actualResult + "</span>";
       Driver.closeDriver();
   }
}