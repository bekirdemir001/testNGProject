package comhubcomfy.tests.us_02;

import com.github.javafaker.Faker;
import comhubcomfy.pages.FakeMailPage;
import comhubcomfy.pages.HomePage;
import comhubcomfy.pages.RegisterPage;
import comhubcomfy.pages.VendorRegisterPage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import org.testng.annotations.Test;

import static comhubcomfy.utilities.Extent_Reports.extentTest;

public class TC012_VendorValidData {
    private final String testName = "US01 || TC012-Vendor enters valid data";
    private final String expectedResult = "Vendor register successfully and show 'Welcome to Hubcomfy!'";
    String actualResult = "Vendor did not get verification code and could not register";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorValidData(){
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("User goes to 'Hubcomfy.com'");
        Faker faker = new Faker();

        HomePage homePage = new HomePage();
        homePage.registerButton.click();
        extentTest.pass("User clicks on 'Register' button");

        RegisterPage registerPage = new RegisterPage();
        registerPage.becomeAVendor.click();
        extentTest.pass("Vendor clicks on 'Become a Vendor'");

        //Go to fake mail address for verification code
        Driver.getDriver().get(ConfigReader.getProperty("fakeMailURL"));
        FakeMailPage fakeMailPage = new FakeMailPage();
        String fakeMail = fakeMailPage.fakeMailAddress.getText();

        VendorRegisterPage vendorRegisterPage = new VendorRegisterPage();
        vendorRegisterPage.emailInputBox.sendKeys(fakeMail);
        extentTest.pass("Vendor enters valid email");












    }
}
