package comAlloverCommerce.tests.us_06;

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

public class TC039_VendorUnRegisteredEmail {
    private final String testName = "US03 || TC039-Vendor enters unregistered email address";
    private final String expectedResult = "Vendor cannot reset his/her password with unregistered email and show 'Invalid username or email.' warning message";
    String actualResult = "Vendor couldn't reset his/her password and showed 'Invalid username or email.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorUnRegisteredEmail(){
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P07_LostPassword lostPassword = new P07_LostPassword();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) Vendor goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) Vendor clicks on 'My Account' button");

        ReusableMethods.jsClick(signInPage.lostYourPassword);
        extentTest.pass("3) Vendor clicks on 'Lost your password?' button");

        lostPassword.usernameOrEmailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("4) Vendor enters unregistered email address");

        lostPassword.resetPasswordButton.click();
        extentTest.pass("5) Vendor clicks on 'Reset Password' button");

        try {
            Assert.assertTrue(lostPassword.lostPasswordWarningMessage.getText().equals("Invalid username or email."));
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