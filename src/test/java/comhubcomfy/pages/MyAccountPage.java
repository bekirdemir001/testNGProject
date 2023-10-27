package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    public MyAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//a[@href='https://hubcomfy.com/my-account-2/customer-logout/']")
    public WebElement signOutButton;

}
