package comhubcomfy.tests.us_01;

import com.github.javafaker.Faker;
import comhubcomfy.pages.P01_HomePage;
import comhubcomfy.utilities.ConfigReader;
import comhubcomfy.utilities.Driver;
import org.testng.annotations.Test;

public class TC01_AllAreasFilled {
    private final String testName = "US01 || TC01-Enter valid data";
    private final String description = "Valid data must be entered in all fields, including username.";
    private final String reportMessage = "User must be able to register successfully";

    @Test(testName = testName, description = "<span style='font-weight:bold'>Amaç:</span> " + description)
    public void successfulCustomerRegistration() {
        Driver.getDriver().get(ConfigReader.getProperty("URL"));
        Faker faker = new Faker();

        P01_HomePage homePage = new P01_HomePage();
        homePage.registerButton.click();
        RaporlamaUtil.extentTestInfo("Home page'den Register sayfasina gidildi.");

        P02_RegisterPage registerPage = new P02_RegisterPage();
        registerPage.userNameBox.sendKeys(faker.name().username());
        registerPage.e_mailBox.sendKeys(faker.internet().emailAddress());
        registerPage.userPasswordBox.sendKeys(ConfigReader.getProperty("generatedPassword"));
        registerPage.policyAgreementBox.click();
        registerPage.userSignUpButton.click();
        RaporlamaUtil.extentTestInfo("SignUp işlemi yapıldı.");

        RaporlamaUtil.extentTestInfo("Username dahil, butun alanlara veri girilebildigi kontrol edildi.");
        Assert.assertTrue(homePage.homePageLogo.isDisplayed());

        ReusableMethods.waitFor(3);
        Driver.closeDriver();
        RaporlamaUtil.message = "<span style='color:green; font-weight:bold; font-size: 14px'>TEST SONUCU: </span><br><span style='color:purple; font-size: 16px'>" + raporMesaji + "</span>";

    }




}
