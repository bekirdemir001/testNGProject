package comAlloverCommerce.tests.us_04;

import comAlloverCommerce.pages.P01_HomePage;
import comAlloverCommerce.pages.P02_UserRegisterPage;
import comAlloverCommerce.pages.P06_SignInPage;
import comAlloverCommerce.utilities.ConfigReader;
import comAlloverCommerce.utilities.Driver;
import comAlloverCommerce.utilities.Extent_Reports;
import comAlloverCommerce.utilities.ReusableMethods;
import org.testng.Assert;
import org.testng.annotations.Test;

import static comAlloverCommerce.utilities.Extent_Reports.extentTest;

public class TC031_VendorUsernameOrEmailBlank {
    private final String testName = "US03 || TC031-Vendor leaves username or email blank";
    private final String expectedResult = "Vendor cannot sign in and show warning message";
    String actualResult = "Vendor couldn't sign in and showed warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorUsernameOrEmailBlank() {
        P01_HomePage homePage = new P01_HomePage();
        P06_SignInPage signInPage = new P06_SignInPage();
        P02_UserRegisterPage userRegisterPage = new P02_UserRegisterPage();

        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        extentTest.pass("1) Vendor goes to homepage");

        ReusableMethods.jsClick(homePage.myAccountButton);
        extentTest.pass("2) Vendor clicks on 'My Account' button");

        extentTest.pass("3) *** Vendor leaves username or email address blank ***");

        signInPage.passwordInputBox.sendKeys(ConfigReader.getProperty("generatedVendorPassword"));
        extentTest.pass("4) Vendor enters registered password");

        ReusableMethods.jsClick(signInPage.signInButton);
        extentTest.pass("5) Vendor clicks on 'Sign In' button");

        try {
            Assert.assertTrue(userRegisterPage.userRegisterPage.isDisplayed());
            Driver.closeDriver();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            Extent_Reports.message = "<span style='color:blue; font-weight:bold; font-size: 16px'>Test Result: </span>" +
                    "<br>" +
                    "<span style='font-size: 16px'>" + actualResult + "</span>";
        }
    }
}
