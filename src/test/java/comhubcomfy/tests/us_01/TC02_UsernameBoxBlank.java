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

public class TC02_UsernameBoxBlank {
    private final String userStory = "US01";
    private final String testName = "US01 || TC02-Username input box is blank";
    private final String description = "Registration isn't completed without entering username";
    private final String reportMessage = "USER ISN'T REGISTERED SUCCESSFULLY!";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Amaç:</span> " + description)
    private void unsuccessfulRegistration() throws IOException {

        Extent_Reports.extentTestInfo("Test starts");

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        Extent_Reports.extentTestInfo("Homepage is opened");
        Faker faker = new Faker();

        P01_HomePage homePage = new P01_HomePage();
        homePage.registerButton.click();
        Extent_Reports.extentTestInfo("Registration page is opened");

        P02_RegisterPage registerPage = new P02_RegisterPage();
        Extent_Reports.extentTestInfo("USERNAME ISN'T ENTERED!");
        registerPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        Extent_Reports.extentTestInfo("Email is entered");
        registerPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedPassword"));
        Extent_Reports.extentTestInfo("Password is entered");
        registerPage.registerPolicy.click();
        Extent_Reports.extentTestInfo("Register privacy policy is clicked");
        registerPage.signUpButton.click();
        Extent_Reports.extentTestInfo("SignUp button is clicked");

        Assert.assertTrue(registerPage.userRegisterPage.isDisplayed());
        ReusableMethods.getScreenshot(userStory);
        Extent_Reports.extentTestInfo("Sign Out button isn't displayed");

        Driver.closeDriver();

        Extent_Reports.extentTestInfo("Test finished");

        Extent_Reports.extentTestPass(reportMessage);

    }
}