package comAlloverCommerce.tests.us_02;

import comAlloverCommerce.pages.*;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC016_VendorTooShortPassword {
    private final String testName = "US02 || TC016-Vendor enters too short password";
    private final String expectedResult = "Vendor shows 'Password strength should be at least Good' warning message and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorTooShortPassword(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P04_VendorRegisterPage vendorRegisterPage = new P04_VendorRegisterPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        FakeMailPage fakeMailPage = new FakeMailPage();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) Vendor goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) Vendor clicks on 'My Account' button");

        myAccountPage.signUpButton.click();
        extentTest.pass("3) Vendor clicks on 'Sign Up' button");

        ReusableMethods.jsClick(userRegisterPage.becomeAVendor);
        extentTest.pass("4) Vendor clicks on 'Become a Vendor'");

        //Go to fake mail address for verification code
        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("fakeMailURL"));

        String fakeMail = fakeMailPage.fakeMailAddress.getText();

        ReusableMethods.switchToWindow(0);

        vendorRegisterPage.emailInputBox.sendKeys(fakeMail);
        extentTest.pass("5) Vendor enters valid email");

        vendorRegisterPage.verificationCodeInputBox.click();
        ReusableMethods.waitFor(5);

        Assert.assertTrue(vendorRegisterPage.emailSentMessage.isDisplayed());
        extentTest.pass("6) Vendor asserts that verification code is sent");

        ReusableMethods.switchToWindow(1);
        ReusableMethods.waitFor(10);

        String verificationCodeMessage = fakeMailPage.verificationCodeEMail.getText();
        String[] verificationCode = verificationCodeMessage.split(" ");

        ReusableMethods.switchToWindow(0);

        vendorRegisterPage.verificationCodeInputBox.sendKeys(verificationCode[6]);
        extentTest.pass("7) Vendor enters 'Verification Code' comes from email");

        vendorRegisterPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedVendorTooShortPassword"));
        extentTest.pass("8) *** Vendor enters too short password ***");

        vendorRegisterPage.confirmPasswordInputBox.sendKeys(ConfigReader.getProperty("generatedVendorTooShortPassword"));
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