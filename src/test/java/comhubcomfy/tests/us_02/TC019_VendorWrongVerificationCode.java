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

public class TC019_VendorWrongVerificationCode {
    private final String testName = "US02 || TC019-Vendor enters wrong verification code";
    private final String expectedResult = "Vendor cannot register and show 'Email verification code invalid' warning message";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorWrongVerificationCode(){
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

        vendorRegisterPage.verificationCodeInputBox.sendKeys(ConfigReader.getProperty("generatedVerificationCode"));
        extentTest.pass("7) Vendor enters generated 'Verification Code'");

        String psw = faker.internet().password();
        vendorRegisterPage.passwordInputBox.sendKeys(psw);
        extentTest.pass("8) Vendor enters valid password");

        vendorRegisterPage.confirmPasswordInputBox.sendKeys(psw);
        extentTest.pass("9) Vendor enters the same password again");

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);
        extentTest.pass("10) Vendor clicks on 'Register' ");

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