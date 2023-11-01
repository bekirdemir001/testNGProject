package comhubcomfy.tests.us_02;

import com.github.javafaker.Faker;
import comhubcomfy.pages.FakeMailPage;
import comhubcomfy.pages.HomePage;
import comhubcomfy.pages.RegisterPage;
import comhubcomfy.pages.VendorRegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.Extent_Reports;
import comhubcomfy.utilities.ReusableMethods;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC016_VendorConfirmPasswordBlank {
    private final String testName = "US02 || TC016-Vendor leaves confirm password blank";
    private final String expectedResult = "Vendor leaves confirm password blank";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorConfirmPasswordBlank(){
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("User goes to 'Hubcomfy.com'");

        HomePage homePage = new HomePage();
        homePage.registerButton.click();
        extentTest.pass("Vendor clicks on 'Register' button");

        RegisterPage registerPage = new RegisterPage();
        registerPage.becomeAVendor.click();
        extentTest.pass("Vendor clicks on 'Become a Vendor'");

        String windowHandle1 = Driver.getDriver().getWindowHandle();

        //Go to fake mail address for verification code
        Driver.getDriver().switchTo().newWindow(WindowType.TAB);
        Driver.getDriver().get(ConfigReader.getProperty("fakeMailURL"));

        String windowsHandle2 = Driver.getDriver().getWindowHandle();

        FakeMailPage fakeMailPage = new FakeMailPage();
        String fakeMail = fakeMailPage.fakeMailAddress.getText();

        Driver.getDriver().switchTo().window(windowHandle1);

        VendorRegisterPage vendorRegisterPage = new VendorRegisterPage();
        vendorRegisterPage.emailInputBox.sendKeys(fakeMail);
        extentTest.pass("Vendor enters valid email");

        vendorRegisterPage.verificationCodeInputBox.click();
        ReusableMethods.waitFor(1);

        Assert.assertTrue(vendorRegisterPage.emailSentMessage.isEnabled());
        extentTest.pass("Vendor shows that verification code is sent");
        ReusableMethods.waitFor(1);

        Driver.getDriver().switchTo().window(windowsHandle2);
        try {
            Assert.assertTrue(fakeMailPage.fakeMailVerificationCode.isEnabled());
        }catch (Exception e){
            e.printStackTrace();
        }
        extentTest.fail("Vendor enters 'Verification Code' comes from email");

        Driver.getDriver().switchTo().window(windowHandle1);

        Faker faker = new Faker();
        vendorRegisterPage.passwordInputBox.sendKeys(faker.internet().password());
        extentTest.pass("Vendor enters valid password");

        extentTest.pass("*** Vendor leaves confirm password blank ***");

        ReusableMethods.waitFor(1);

        ReusableMethods.jsClick(vendorRegisterPage.registerButton);
        extentTest.pass("Vendor clicks on 'Register'");

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