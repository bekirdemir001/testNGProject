package comhubcomfy.tests.us_03;

import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.pages.P03_MyAccountPage;
import comhubcomfy.pages.P06_SignInPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC021_UserRegisteredData {
    private final String testName = "US03 || TC021-User enters registered information";
    private final String expectedResult = "User can sign in successfully and show 'Dashboard'";
    String actualResult = "User signed in successfully and showed 'Dashboard'";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void userRegisteredData(){
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) User goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) User clicks on 'My Account' button");

        signInPage.usernameOrEmailAddressInputBox.sendKeys(ConfigReader.getProperty("generatedUserUsername"));
        extentTest.pass("3) User enters registered username or email address");

        signInPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedUserPassword"));
        extentTest.pass("4) User enters registered password");

        ReusableMethods.jsClick(signInPage.signInButton);
        extentTest.pass("5) User clicks on 'Sign In' button");

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