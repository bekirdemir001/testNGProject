package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    public HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//a[@href='https://hubcomfy.com/my-account-2/'][2]")
    public WebElement registerButton;

}