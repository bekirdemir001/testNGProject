package comAlloverCommerce.tests.us_03;

import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P02_UserRegisterPage;
import comAlloverCommerce.pages.P06_SignInPage;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC026_UserPasswordBlank {
    private final String testName = "US03 || TC026-User leaves password blank";
    private final String expectedResult = "User cannot sign in and show warning message";
    String actualResult = "User couldn't sign in and showed warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void userPasswordBlank() {
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) User goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) User clicks on 'My Account' button");

        signInPage.usernameOrEmailAddressInputBox.sendKeys(ConfigReader.getProperty("generatedUserUsername"));
        extentTest.pass("3) User enters registered username or email address");

        extentTest.pass("4) *** User leaves password blank ***");

        ReusableMethods.jsClick(signInPage.signInButton);
        extentTest.pass("5) User clicks on 'Sign In' button");

        try {
            Assert.assertTrue(userRegisterPage.userRegisterPage.isDisplayed());
            Driver.closeDriver();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }
}