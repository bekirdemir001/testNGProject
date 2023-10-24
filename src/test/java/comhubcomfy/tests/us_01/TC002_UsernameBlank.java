package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P02_RegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC002_UsernameBlank {
    String testCaseID = "US01_TC002";
    private final String testName = "US01 || TC002-User leaves username blank";
    private final String expectedResult = "User cannot register and show warning message";
    String actualResult = "User did not register and showed warning message";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result:</span> " + expectedResult)
    private void unsuccessfulCostumerRegistration() throws IOException {

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("User goes to 'Hubcomfy.com'");
        Faker faker = new Faker();

        P01_HomePage homePage = new P01_HomePage();
        homePage.registerButton.click();
        extentTest.pass("User clicks on 'Register' button");

        P02_RegisterPage registerPage = new P02_RegisterPage();

        extentTest.pass("*** User leaves username blank ***");

        registerPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("User enters valid email");
        registerPage.passwordInputBox.sendKeys(faker.internet().password());
        extentTest.pass("User enters valid password");
        registerPage.privacyPolicy.click();
        extentTest.pass("User clicks on privacy policy");
        registerPage.signUpButton.click();
        extentTest.pass("User clicks on 'Sign Up' button");

        Assert.assertTrue(registerPage.userRegisterPage.isDisplayed());
        ReusableMethods.getScreenshot(testCaseID);

        Extent_Reports.message =
                "<span style='color:green; font-weight:bold; font-size: 14px'>Test Result: </span>" +
                        "<br>" +
                        "<span style='color:purple; font-size: 16px'>" + actualResult + "</span>";
        Driver.closeDriver();
    }
}