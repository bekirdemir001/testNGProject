package comAlloverCommerce.tests.us_05;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P06_SignInPage;
import comAlloverCommerce.pages.P07_LostPassword;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC37_UsernameOrEmailBlank {
    private final String testName = "US03 || TC037-User leaves username or email input box blank";
    private final String expectedResult = "User cannot reset his/her password and show 'Enter a username or email address.' warning message";
    String actualResult = "User couldn't reset his/her password and showed 'Enter a username or email address.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void usernameOrEmailBlank(){
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P07_LostPassword lostPassword = new P07_LostPassword();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) User goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) User clicks on 'My Account' button");

        ReusableMethods.jsClick(signInPage.lostYourPassword);
        extentTest.pass("3) User clicks on 'Lost your password?' button");

        extentTest.pass("4) User leaves username or email input box blank");

        lostPassword.resetPasswordButton.click();
        extentTest.pass("5) User clicks on 'Reset Password' button");

        try {
            Assert.assertTrue(lostPassword.lostPasswordWarningMessage.getText().equals("Enter a username or email address."));
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
