package comAlloverCommerce.tests.us_05;

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

public class TC034_UserRegisteredEmail {
    private final String testName = "US03 || TC034-User enters registered email address";
    private final String expectedResult = "User can reset his/her password and show 'Your password has been reset successfully.' warning message";
    String actualResult = "User can reset his/her password and show 'Your password has been reset successfully.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void userRegisteredEmail(){
        P06_SignInPage signInPage = new P06_SignInPage();
        P07_LostPassword lostPassword = new P07_LostPassword();
        FakeMailPage2 fakeMailPage2 = new FakeMailPage2();
        P03_MyAccountPage myAccountPage = new P03_MyAccountPage();
        Faker faker = new Faker();

        String email = register();

        extentTest.pass("1) User goes to homepage");

        extentTest.pass("2) User clicks on 'My Account' button");

        ReusableMethods.jsClick(signInPage.lostYourPassword);
        extentTest.pass("3) User clicks on 'Lost your password?' button");

        lostPassword.usernameOrEmailInputBox.sendKeys(email);
        extentTest.pass("4) User enters registered username");

        lostPassword.resetPasswordButton.click();
        extentTest.pass("5) User clicks on 'Reset Password' button");

        ReusableMethods.waitForVisibility(lostPassword.resetEmailSentMessage, 10);

        Assert.assertTrue(lostPassword.resetEmailSentMessage.isDisplayed());
        extentTest.pass("6) User asserts that 'Password reset email has been sent.' message is shown");

        ReusableMethods.switchToWindow(1);
        ReusableMethods.waitFor(10);

        ReusableMethods.jsClick(fakeMailPage2.resetMail);
        ReusableMethods.waitFor(3);

        Driver.getDriver().switchTo().frame("iframe");

        ReusableMethods.jsClick(fakeMailPage2.resetPasswordLink);
        ReusableMethods.waitFor(5);
        extentTest.pass("7) User goes to their email and click on 'Click here to reset your password'");

        ReusableMethods.switchToWindow(2);

        String password = faker.internet().password();
        lostPassword.newPasswordInputBox.sendKeys(password);
        extentTest.pass("8) User enters new password");

        lostPassword.reEnterNewPasswordInputBox.sendKeys(password);
        extentTest.pass("9) User enters new password again");

        ReusableMethods.jsClick(lostPassword.saveButton);
        extentTest.pass("10) User clicks on 'Save' button");

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
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        ReusableMethods.jsClick(homePage.myAccountButton);
        myAccountPage.signUpButton.click();

        String username = faker.name().username();
        userRegisterPage.usernameInputBox.sendKeys(username);

        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("fakeMailURL2"));
        String email = ReusableMethods.getValueByJS("eposta_adres");

        ReusableMethods.switchToWindow(0);
        userRegisterPage.emailInputBox.sendKeys(email);

        String password = faker.internet().password();
        userRegisterPage.passwordInputBox.sendKeys(password);

        ReusableMethods.jsClick(userRegisterPage.privacyPolicy);

        ReusableMethods.jsClick(userRegisterPage.signUpButton);

        Assert.assertTrue(myAccountPage.dashboard.isDisplayed());

        ReusableMethods.jsClick(myAccountPage.logout);

        return email;
    }
}
