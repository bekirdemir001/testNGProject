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

public class TC01_AllAreasFilled {
    private final String userStory = "US01";
    private final String testName = "US01 || TC01-Enter valid data";
    private final String description = "Valid data must be entered in all fields, including username";
    private final String reportMessage = "USER IS REGISTERED SUCCESSFULLY";


   @Test(testName = testName, description = "<span style='font-weight:bold'>Amaç:</span> " + description)
   public void successfulCustomerRegistration() throws IOException {
       Extent_Reports.startExtentReports(testName, description, userStory);
       Extent_Reports.createExtentTest(testName, description);

       Extent_Reports.extentTestInfo("Test starts");

       Driver.getDriver().get(ConfigReader.getProperty("URL"));
       Extent_Reports.extentTestInfo("Homepage is opened");
       Faker faker = new Faker();

       P01_HomePage homePage = new P01_HomePage();
       homePage.registerButton.click();
       Extent_Reports.extentTestInfo("Registration page is opened");

       P02_RegisterPage registerPage = new P02_RegisterPage();
       registerPage.usernameInputBox.sendKeys(faker.name().username());
       Extent_Reports.extentTestInfo("Username is entered");
       registerPage.emailInputBox.sendKeys(faker.internet().emailAddress());
       Extent_Reports.extentTestInfo("Email is entered");
       registerPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedPassword"));
       Extent_Reports.extentTestInfo("Password is entered");
       registerPage.registerPolicy.click();
       Extent_Reports.extentTestInfo("Register privacy policy is clicked");
       registerPage.signUpButton.click();
       Extent_Reports.extentTestInfo("SignUp button is clicked");

       Assert.assertTrue(registerPage.signOutButton.isDisplayed());
       ReusableMethods.getScreenshot(userStory);
       Extent_Reports.extentTestInfo("It is controlled that all areas are filled");

       Driver.closeDriver();

       Extent_Reports.extentTestInfo("Test finished");

       Extent_Reports.tearDown();


       //Extent_Reports.extentTestPass(reportMessage);

   }

}
