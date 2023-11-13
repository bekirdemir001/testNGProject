package comAlloverCommerce.tests.us_04;

import com.github.javafaker.Faker;
import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P06_SignInPage;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC029_VendorUnRegisteredPassword {
    private final String testName = "US03 || TC023-Vendor enters unregistered password";
    private final String expectedResult = "Vendor cannot sign in and show 'Wrong username or password.' warning message";
    String actualResult = "Vendor couldn't sign in and showed 'Wrong username or password.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorUnRegisteredPassword(){
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        Faker faker = new Faker();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) Vendor goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) Vendor clicks on 'My Account' button");

        signInPage.usernameOrEmailAddressInputBox.sendKeys(ConfigReader.getProperty("generatedVendorEmail"));
        extentTest.pass("3) Vendor enters registered email address");

        signInPage.passwordInputBox.sendKeys(faker.internet().password());
        extentTest.pass("4) *** Vendor enters unregistered password ***");

        ReusableMethods.jsClick(signInPage.signInButton);
        extentTest.pass("5) Vendor clicks on 'Sign In' button");

        try {
            Assert.assertTrue(signInPage.warningMessage.isDisplayed());
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