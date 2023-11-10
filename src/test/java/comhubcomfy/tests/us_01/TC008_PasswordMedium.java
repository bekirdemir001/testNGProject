package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P02_UserRegisterPage;
import comhubcomfy.pages.P03_MyAccountPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC008_PasswordMedium {
    private final String testName = "US01 || TC008-User enters medium password";
    private final String expectedResult = "'Sign Up' button is clickable and user registers successfully";
    String actualResult = "User registered successfully with medium password ";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void passwordMedium(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) User goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) User clicks on 'My Account' button");

        myAccountPage.signUpButton.click();
        extentTest.pass("3) Vendor clicks on 'Sign Up' button");

        userRegisterPage.usernameInputBox.sendKeys(faker.name().username());
        extentTest.pass("4) User enters valid username");

        userRegisterPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("5) User enters valid email");

        userRegisterPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedMediumPassword"));
        extentTest.pass("6) *** User enters medium password ***");

        ReusableMethods.jsClick(userRegisterPage.privacyPolicy);
        extentTest.pass("7) User clicks on privacy policy");

        ReusableMethods.jsClick(userRegisterPage.signUpButton);
        extentTest.pass("8) User clicks on 'Sign Up' button");

        try {
            Assert.assertTrue(myAccountPage.dashboard.isDisplayed());
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