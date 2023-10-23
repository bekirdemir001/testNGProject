package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import comhubcomfy.utilities.ReusableMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P01_HomePage {
    public P01_HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(xpath = "(//a[@class='logo'])[1]")
    public WebElement homePageLogo;
    @FindBy (xpath = "//a[@href='https://hubcomfy.com/my-account-2/'][2]")
    public WebElement registerButton;

    @FindBy(xpath = "//a[contains(text(), 'My Account')]")
    public WebElement myAccount;
}
