package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P07_LostPassword {

    public P07_LostPassword() {
    PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user_login")
    public WebElement usernameOrEmailInputBox;

    @FindBy(xpath = "//button[@class='woocommerce-Button button']")
    public WebElement resetPasswordButton;

    @FindBy(xpath = "//div[@class='woocommerce-message alert alert-simple alert-icon alert-close-top alert-success']")
    public WebElement resetEmailSentMessage;

    @FindBy(id = "password_1")
    public WebElement newPasswordInputBox;

    @FindBy(id = "password_2")
    public WebElement reEnterNewPasswordInputBox;

    @FindBy(xpath = "//button[@class='woocommerce-Button button']")
    public WebElement saveButton;

    @FindBy(xpath = "//li[@class=' alert alert-simple alert-icon alert-close-top alert-danger']")
    public WebElement lostPasswordWarningMessage;
}