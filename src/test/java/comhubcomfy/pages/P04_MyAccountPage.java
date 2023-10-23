package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P04_MyAccountPage {
    public P04_MyAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "customer_login")
    public WebElement costumerLoginPage;
    @FindBy(xpath = "//a[@href= 'signup']")
    public WebElement signUpLink;

    @FindBy(id = "reg_username")
    public WebElement usernameInputBox;

    @FindBy(id = "reg_email")
    public WebElement emailInputBox;

    @FindBy(id = "reg_password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//div[@class = 'woocommerce-password-strength short']")
    public WebElement veryWeakWarningMessage;

    @FindBy(xpath = "//div[@class = 'woocommerce-password-strength bad']")
    public WebElement weakWarningMessage;

    @FindBy(xpath = "//div[@class = 'woocommerce-password-strength good']")
    public WebElement mediumWarningMessage;

    @FindBy(xpath = "//div[@class = 'woocommerce-password-strength strong']")
    public WebElement strongWarningMessage;

    @FindBy(id = "register-policy")
    public WebElement privacyPolicy;

    @FindBy(xpath = "//button[@name='register']")
    public WebElement signUpButton;

//*******************************************************************************************
    @FindBy(xpath = "//a[@href= 'https://hubcomfy.com/my-account-2/']")
    public WebElement dashboard;
}
