package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    public RegisterPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(id = "customer_login")
    public WebElement userRegisterPage;

    @FindBy (id = "reg_username")
    public WebElement usernameInputBox;

    @FindBy (id = "reg_email")
    public WebElement emailInputBox;

    @FindBy (id = "reg_password")
    public WebElement passwordInputBox;

    @FindBy (id = "register-policy")
    public WebElement privacyPolicy;

    @FindBy (name = "register")
    public WebElement signUpButton;

    @FindBy(xpath = "//div[@class='wcfmmp_become_vendor_link']")
    public WebElement becomeAVendor;

}