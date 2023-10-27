package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorRegisterPage {
    public VendorRegisterPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user_email")
    public WebElement emailInputBox;

    @FindBy(xpath = "//input[@name='wcfm_email_verified_input']")
    public WebElement verificationCodeInputBox;

    @FindBy(xpath = "//input[@name='wcfm_email_verified_button']")
    public WebElement reSendCodeButton;

    @FindBy(xpath = "//div[@class='wcfm-message email_verification_message wcfm-success']")
    public WebElement emailSentMessage;

    @FindBy(id = "passoword")
    public WebElement passwordInputBox;

    @FindBy(id = "password_strength")
    public WebElement passwordWarningMessage;

    @FindBy(id = "confirm_pwd")
    public WebElement confirmPasswordInputBox;

    @FindBy(xpath = "//div[@class='wcfm-message']")
    public WebElement registrationWarningMessage;

    @FindBy(id = "wcfm_membership_register_button")
    public WebElement registerButton;

}