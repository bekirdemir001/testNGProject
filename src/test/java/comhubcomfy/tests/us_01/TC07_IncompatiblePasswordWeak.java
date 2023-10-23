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

public class TC07_IncompatiblePasswordWeak {
    private final String testName = "US01 || TC07-Weak Password Message ";
    private final String description = "Warning message is shown and registration should not completed";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Target:</span> " + description)
    public void unsuccessfulCostumerRegistration() throws IOException {
        String testCaseNumber = "US01_TC07";
        String reportMessage = "User did not register and showed 'Weak - Please enter a stronger password' warning message!";

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
        myAccountPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedWeakPassword"));
        extentTest.pass("*** WEAK PASSWORD IS ENTERED ***");

        Actions actions=new Actions(Driver.getDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        ReusableMethods.waitForVisibility(myAccountPage.weakWarningMessage, 15);
        Assert.assertTrue(myAccountPage.weakWarningMessage.isDisplayed());

        ReusableMethods.jsClick(myAccountPage.privacyPolicy);
        extentTest.pass("Register privacy policy is clicked");

        Assert.assertTrue(myAccountPage.costumerLoginPage.isDisplayed());
        ReusableMethods.getScreenshot(testCaseNumber);
        extentTest.pass("It is controlled that 'Weak - Please enter a stronger password' warning message is shown");

        Driver.closeDriver();
        Extent_Reports.message =
                "<span style='color:green; font-weight:bold; font-size: 14px'>Test Result: </span>" +
                        "<br>" +
                        "<span style='color:purple; font-size: 16px'>" + reportMessage + "</span>";
    }
}