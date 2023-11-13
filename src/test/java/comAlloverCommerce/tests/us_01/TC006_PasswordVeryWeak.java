package comAlloverCommerce.tests.us_01;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P02_UserRegisterPage;
import comAlloverCommerce.pages.P03_MyAccountPage;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC006_PasswordVeryWeak {
    private final String testName = "US01 || TC006-User enters very weak password";
    private final String expectedResult = "'Sign Up' button isn't clickable and user cannot register";
    String actualResult = "'Sign Up' button isn't clickable and user couldn't register";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result:</span> " + expectedResult)
    public void passwordVeryWeak(){
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

        userRegisterPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedVeryWeakPassword"));
        extentTest.pass("6) *** User enters very weak password ***");

        ReusableMethods.jsClick(userRegisterPage.privacyPolicy);
        extentTest.pass("7) User clicks on privacy policy");

        ReusableMethods.jsClick(userRegisterPage.signUpButton);
        extentTest.pass("8) User clicks on 'Sign Up' button");

        try {
            Assert.assertTrue(userRegisterPage.userRegisterPage.isDisplayed());
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