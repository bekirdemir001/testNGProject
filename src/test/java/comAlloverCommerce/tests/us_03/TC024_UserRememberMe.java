package comAlloverCommerce.tests.us_03;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P03_MyAccountPage;
import comAlloverCommerce.pages.P06_SignInPage;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC024_UserRememberMe {
    private final String testName = "US03 || TC024-User clicks on 'Remember me'";
    private final String expectedResult = "User can show their credentials when his or her reaches 'My Account' page again";
    String actualResult = "User couldn't show their credentials and they enter them again";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void userRememberMe() {
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) User goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) User clicks on 'My Account' button");

        signInPage.usernameOrEmailAddressInputBox.sendKeys(ConfigReader.getProperty("generatedUserEmail"));
        extentTest.pass("3) User enters registered username or email address");

        signInPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedUserPassword"));
        extentTest.pass("4) User enters registered password");

        ReusableMethods.jsClick(signInPage.rememberMeCheckBox);
        extentTest.pass("5) User clicks on 'Remember me'");

        ReusableMethods.jsClick(signInPage.signInButton);
        extentTest.pass("6) User clicks on 'Sign In' button");

        Assert.assertTrue(myAccountPage.dashboard.isDisplayed());
        extentTest.pass("7) User asserts that 'Dashboard' is displayed");

        ReusableMethods.jsClick(myAccountPage.logout);
        extentTest.pass("8) User clicks on 'Logout' button");

        try {
            Assert.assertTrue(signInPage.usernameOrEmailAddressInputBox.getText().equals(ConfigReader.getProperty("generatedUserEmail")));
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