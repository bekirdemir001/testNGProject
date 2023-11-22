package comAlloverCommerce.tests.us_06;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.*;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC038_VendorRegisteredEmail {
    private final String testName = "US03 || TC038-Vendor enters registered email";
    private final String expectedResult = "Vendor can reset his/her password and show 'Your password has been reset successfully.' warning message";
    String actualResult = "Vendor could reset his/her password and showed 'Your password has been reset successfully.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorRegisteredEmail(){
        P06_SignInPage signInPage = new P06_SignInPage();
        P07_LostPassword lostPassword = new P07_LostPassword();
        FakeMailPage2 fakeMailPage2 = new FakeMailPage2();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        Faker faker = new Faker();

        String email = register();

        extentTest.pass("1) Vendor goes to homepage");

        extentTest.pass("2) Vendor clicks on 'My Account' button");

        ReusableMethods.jsClick(signInPage.lostYourPassword);
        extentTest.pass("3) Vendor clicks on 'Lost your password?' button");

        lostPassword.usernameOrEmailInputBox.sendKeys(email);
        extentTest.pass("4) Vendor enters registered email");

        lostPassword.resetPasswordButton.click();
        extentTest.pass("5) Vendor clicks on 'Reset Password' button");

        ReusableMethods.waitForVisibility(lostPassword.resetEmailSentMessage, 10);

        Assert.assertTrue(lostPassword.resetEmailSentMessage.isDisplayed());
        extentTest.pass("6) Vendor asserts that 'Password reset email has been sent.' message is shown");

        ReusableMethods.switchToWindow(1);
        fakeMailPage2.backButton.click();
        ReusableMethods.waitFor(10);

        ReusableMethods.jsClick(fakeMailPage2.firstMail);
        ReusableMethods.waitFor(3);

        Driver.getDriver().switchTo().frame("iframe");

        ReusableMethods.jsClick(fakeMailPage2.resetPasswordLink);
        ReusableMethods.waitFor(5);
        extentTest.pass("7) Vendor goes to their email and click on 'Click here to reset your password'");

        ReusableMethods.switchToWindow(2);

        String password = faker.internet().password();
        lostPassword.newPasswordInputBox.sendKeys(password);
        extentTest.pass("8) Vendor enters new password");

        lostPassword.reEnterNewPasswordInputBox.sendKeys(password);
        extentTest.pass("9) Vendor enters new password again");

        ReusableMethods.jsClick(lostPassword.saveButton);
        extentTest.pass("10) Vendor clicks on 'Save' button");

        ReusableMethods.waitFor(3);

        try {
            Assert.assertTrue(myAccountPage.resetPasswordSuccessfulMessage.isDisplayed());
            Driver.closeDriver();
        }catch (Exception e){
            throw new RuntimeException();
        }finally {
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }

    public String register(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        P04_VendorRegisterPage vendorRegisterPage = new P04_VendorRegisterPage();
        FakeMailPage2 fakeMailPage2 = new FakeMailPage2();
        P05_VendorAccountPage vendorAccountPage = new P05_VendorAccountPage();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        ReusableMethods.jsClick(homePage.myAccountButton);
        myAccountPage.signUpButton.click();

        ReusableMethods.jsClick(userRegisterPage.becomeAVendor);

        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("fakeMailURL2"));
        String email = ReusableMethods.getValueByJS("eposta_adres");

        ReusableMethods.switchToWindow(0);
        vendorRegisterPage.emailInputBox.sendKeys(email);

        vendorRegisterPage.verificationCodeInputBox.click();
        ReusableMethods.waitFor(5);

        Assert.assertTrue(vendorRegisterPage.emailSentMessage.isDisplayed());

        ReusableMethods.switchToWindow(1);
        ReusableMethods.waitFor(10);

        ReusableMethods.jsClick(fakeMailPage2.firstMail);
        ReusableMethods.waitFor(3);

        Driver.getDriver().switchTo().frame("iframe");

        String verificationCodeMessage = fakeMailPage2.verificationCode.getText();

        ReusableMethods.switchToWindow(0);

        vendorRegisterPage.verificationCodeInputBox.
                sendKeys(verificationCodeMessage.substring(verificationCodeMessage.length()-6));

        ReusableMethods.waitFor(5);

        String psw = faker.internet().password();
        vendorRegisterPage.passwordInputBox.sendKeys(psw);

        vendorRegisterPage.confirmPasswordInputBox.sendKeys(psw);

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);

        ReusableMethods.waitFor(3);

        vendorAccountPage.notRightNowButton.click();

        ReusableMethods.jsClick(vendorAccountPage.logoutButton);

        ReusableMethods.waitFor(3);

        return email;
    }
}