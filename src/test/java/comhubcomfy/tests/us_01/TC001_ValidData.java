package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.HomePage;
import comhubcomfy.pages.MyAccountPage;
import comhubcomfy.pages.RegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import org.testng.Assert;
import org.testng.annotations.*;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC001_ValidData {
    private final String testName = "US01 || TC001-User enters valid data";
    private final String expectedResult = "User registered successfully and showed 'Sign Out'";
    String actualResult = "User registered successfully and showed 'Sign Out'";

   @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
   public void validData(){

       Driver.getDriver().get(ConfigReader.getProperty("URL"));
       extentTest.pass("User goes to 'Hubcomfy.com'");
       Faker faker = new Faker();

       HomePage homePage = new HomePage();
       homePage.registerButton.click();
       extentTest.pass("User clicks on 'Register' button");

       RegisterPage registerPage = new RegisterPage();
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

       MyAccountPage myAccountPage = new MyAccountPage();

       try {
           Assert.assertTrue(myAccountPage.signOutButton.isEnabled());
           Driver.closeDriver();
       }catch (Exception e){
           throw new RuntimeException();
       }finally {
           Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                   "<br>" +
                   "<span style='font-size: 16px'>" + actualResult + "</span>";
       }
   }
}