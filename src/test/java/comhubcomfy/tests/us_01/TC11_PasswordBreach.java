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

public class TC11_PasswordBreach {
    private final String testName = "US01 || TC11-Password with single character";
    private final String description = "Registration isn't completed by entering password with single character";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Target:</span> " + description)
    public void successfulCustomerRegistration() throws IOException {
        String testCaseNumber = "US01_TC11";
        String reportMessage = "User registered and did not show warning message!";

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
        registerPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedSingleCharacterPassword"));
        extentTest.pass("Password is entered");
        registerPage.privacyPolicy.click();
        extentTest.pass("Privacy policy is clicked");
        registerPage.signUpButton.click();
        extentTest.pass("SignUp button is clicked");
        extentTest.pass("It is controlled that all areas are filled, but password has single character");

        try {
            Assert.assertFalse(registerPage.signOutButton.isEnabled());
        } catch (AssertionError e) {
            throw e;
        } finally {
            ReusableMethods.getScreenshot(testCaseNumber);
            Extent_Reports.message = "<span style='color:red; font-weight:bold; font-size: 16px'>BUG FOUND: &#x1F41E</span>" +
                    "<br>" +
                    "<span style='color:purple; font-size: 16px'>" + reportMessage + "</span>";
            Driver.closeDriver();
        }
    }
}