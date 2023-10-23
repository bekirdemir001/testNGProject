package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P04_MyAccountPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC08_AcceptablePasswordMedium {
    private final String testName = "US01 || TC08-Medium Password Message ";
    private final String description = "Warning message is shown and registration should be completed";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Target:</span> " + description)
    public void successfulCostumerRegistration() throws IOException {
        String testCaseNumber = "US01_TC08";
        String reportMessage = "User registered and showed 'Medium' warning message!";

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("Homepage is opened");
        Faker faker = new Faker();

        P01_HomePage homePage = new P01_HomePage();
        ReusableMethods.jsClick(homePage.myAccount);
        extentTest.pass("My Account page is opened");

        P04_MyAccountPage myAccountPage = new P04_MyAccountPage();
        myAccountPage.signUpLink.click();
        extentTest.pass("Sign Up link is clicked");
        myAccountPage.usernameInputBox.sendKeys(faker.name().username());
        extentTest.pass("Username is entered");
        myAccountPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("Email is entered");
        myAccountPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedMediumPassword"));
        extentTest.pass("*** MEDIUM PASSWORD IS ENTERED ***");
        Assert.assertTrue(myAccountPage.mediumWarningMessage.isDisplayed());

        ReusableMethods.jsClick(myAccountPage.privacyPolicy);
        extentTest.pass("Register privacy policy is clicked");

        ReusableMethods.jsClick(myAccountPage.signUpButton);
        extentTest.pass("Sign up button is clicked");

        Assert.assertTrue(myAccountPage.dashboard.isDisplayed());
        ReusableMethods.waitFor(1);
        ReusableMethods.getScreenshot(testCaseNumber);
        extentTest.pass("It is controlled that 'Medium' warning message is shown and registration is completed");

        Driver.closeDriver();
        Extent_Reports.message =
                "<span style='color:green; font-weight:bold; font-size: 14px'>Test Result: </span>" +
                        "<br>" +
                        "<span style='color:purple; font-size: 16px'>" + reportMessage + "</span>";
    }
}
