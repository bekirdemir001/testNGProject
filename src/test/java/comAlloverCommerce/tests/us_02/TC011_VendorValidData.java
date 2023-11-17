package comAlloverCommerce.tests.us_02;

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

public class TC011_VendorValidData {
    private final String testName = "US02 || TC011-Vendor enters valid data";
    private final String expectedResult = "Vendor register successfully and show 'Welcome to Allover Commerce!";
    String actualResult = "Vendor registered successfully and showed 'Welcome to Allover Commerce!'";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorValidData(){
        P01_HomePage homePage = new P01_HomePage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();
        P04_VendorRegisterPage vendorRegisterPage = new P04_VendorRegisterPage();
        P05_VendorAccountPage vendorAccountPage = new P05_VendorAccountPage();
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

        ReusableMethods.switchToWindow(0);

        vendorRegisterPage.verificationCodeInputBox.
                sendKeys(verificationCodeMessage.substring(verificationCodeMessage.length()-6));
        extentTest.pass("7) Vendor enters 'Verification Code' comes from email");

        ReusableMethods.waitFor(5);

        String psw = faker.internet().password();
        vendorRegisterPage.passwordInputBox.sendKeys(psw);
        extentTest.pass("8) Vendor enters valid password");

        vendorRegisterPage.confirmPasswordInputBox.sendKeys(psw);
        extentTest.pass("9) Vendor enters the same password again");

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);
        extentTest.pass("10) Vendor clicks on 'Register'");

        try {
            Assert.assertTrue(vendorAccountPage.vendorAccountText.isDisplayed());
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