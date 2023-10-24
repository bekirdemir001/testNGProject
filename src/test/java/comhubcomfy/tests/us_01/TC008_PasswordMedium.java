package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P02_RegisterPage;
import comhubcomfy.pages.P04_MyAccountPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC008_PasswordMedium {
    String testCaseID = "US01_TC008";
    private final String testName = "US01 || TC008-User enters medium password";
    private final String expectedResult = "'Sign Up' button is clickable and user registers successfully";
    String actualResult = "User registered successfully and showed 'Sign Out', but didn't show warning message";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void unsuccessfulCostumerRegistration() throws IOException {

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

        registerPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedMediumPassword"));
        extentTest.pass("*** User enters medium password ***");

        registerPage.privacyPolicy.click();
        extentTest.pass("User clicks on privacy policy");
        registerPage.signUpButton.click();
        extentTest.pass("User clicks on 'Sign Up' button");

        try {
            Assert.assertFalse(registerPage.signOutButton.isEnabled());
        } catch (AssertionError e) {
            throw e;
        } finally {
            ReusableMethods.waitFor(1);
            ReusableMethods.getScreenshot(testCaseID);
            Extent_Reports.message = "<span style='color:red; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='color:purple; font-size: 16px'>" + actualResult + "</span>";
            Driver.closeDriver();
        }
    }
}
