package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P06_SignInPage {

    public P06_SignInPage(){
    PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "username")
    public WebElement usernameOrEmailAddressInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//button[@name='login']")
    public WebElement signInButton;

    @FindBy(id = "rememberme")
    public WebElement rememberMeCheckBox;

    @FindBy(xpath = "//p[text()='Wrong username or password.']")
    public WebElement warningMessage;

}
