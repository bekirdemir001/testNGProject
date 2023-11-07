package comhubcomfy.tests.us_02;

import com.github.javafaker.Faker;
import comhubcomfy.pages.*;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC015_VendorConfirmPasswordBlank {
    private final String testName = "US02 || TC015-Vendor leaves confirm password blank";
    private final String expectedResult = "Vendor leaves confirm password blank";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorConfirmPasswordBlank(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P04_VendorRegisterPage vendorRegisterPage = new P04_VendorRegisterPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        FakeMailPage fakeMailPage = new FakeMailPage();
        Faker faker = new Faker();

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

        vendorRegisterPage.passwordInputBox.sendKeys(faker.internet().password());
        extentTest.pass("8) Vendor enters valid password");

        extentTest.pass("9) *** Vendor leaves confirm password blank ***");

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);
        extentTest.pass("10) Vendor clicks on 'Register'");

        ReusableMethods.waitFor(3);

        try {
            Assert.assertTrue(vendorRegisterPage.registrationWarningMessage.isEnabled());
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