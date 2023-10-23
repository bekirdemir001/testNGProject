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

public class TC05_PasswordBoxBlank {
    private final String testName = "US01 || TC05-Blank Password";
    private final String description = "Registration isn't completed without entering password";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Target:</span> " + description)
    public void unsuccessfulCostumerRegistration() throws IOException {
        String testCaseNumber = "US01_TC05";
        String reportMessage = "User did not register and showed alert message!";

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("Homepage is opened");
        Faker faker = new Faker();

        P01_HomePage homePage = new P01_HomePage();
        homePage.registerButton.click();
        extentTest.pass("Registration page is opened");

        P02_RegisterPage registerPage = new P02_RegisterPage();
        registerPage.usernameInputBox.sendKeys(faker.name().username());
        extentTest.pass("Username is entered");
        registerPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("Email is entered");

        extentTest.pass("*** PASSWORD IS NOT ENTERED ***");
        registerPage.privacyPolicy.click();
        extentTest.pass("Register privacy policy is clicked");
        registerPage.signUpButton.click();
        extentTest.pass("SignUp button is clicked");

        Assert.assertTrue(registerPage.userRegisterPage.isDisplayed());
        ReusableMethods.getScreenshot(testCaseNumber);
        extentTest.pass("It is controlled that all areas are filled except password");

        Driver.closeDriver();
        Extent_Reports.message =
                "<span style='color:green; font-weight:bold; font-size: 14px'>Test Result: </span>" +
                        "<br>" +
                        "<span style='color:purple; font-size: 16px'>" + reportMessage + "</span>";
    }
}
