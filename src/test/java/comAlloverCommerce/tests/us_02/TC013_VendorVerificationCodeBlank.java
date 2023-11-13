package comAlloverCommerce.tests.us_02;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.*;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC013_VendorVerificationCodeBlank {
    private final String testName = "US02 || TC013-Vendor leaves verification code blank";
    private final String expectedResult = "Vendor shows 'Email Verification Code' is required and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorVerificationCodeBlank(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P04_VendorRegisterPage vendorRegisterPage = new P04_VendorRegisterPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) Vendor goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) Vendor clicks on 'My Account' button");

        myAccountPage.signUpButton.click();
        extentTest.pass("3) Vendor clicks on 'Sign Up' button");

        ReusableMethods.jsClick(userRegisterPage.becomeAVendor);
        extentTest.pass("4) Vendor clicks on 'Become a Vendor'");

        vendorRegisterPage.emailInputBox.sendKeys(faker.internet().emailAddress());
        extentTest.pass("5) Vendor enters valid email");

        vendorRegisterPage.verificationCodeInputBox.click();
        Assert.assertTrue(vendorRegisterPage.emailSentMessage.isEnabled());
        extentTest.pass("6) Vendor asserts that verification code is sent");

        extentTest.pass("7) *** Vendor leaves verification code input box blank ***");

        String psw = faker.internet().password();
        vendorRegisterPage.passwordInputBox.sendKeys(psw);
        extentTest.pass("8) Vendor enters valid password");

        vendorRegisterPage.confirmPasswordInputBox.sendKeys(psw);
        extentTest.pass("9) Vendor enters the same password again");

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);
        extentTest.pass("10) Vendor clicks on 'Register' ");

        ReusableMethods.waitFor(3);

        try {
            Assert.assertTrue(vendorRegisterPage.registrationWarningMessage.isDisplayed());
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