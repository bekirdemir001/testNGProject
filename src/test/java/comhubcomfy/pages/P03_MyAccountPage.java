package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P03_MyAccountPage {
    public P03_MyAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Sign Up']")
    public WebElement signUpButton;
    @FindBy (xpath = "//a[text()='My Account']")
    public WebElement myAccountText;

}
