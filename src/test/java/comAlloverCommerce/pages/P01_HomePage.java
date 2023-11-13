package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P01_HomePage {
    public P01_HomePage() {
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (linkText = "My Account")
    public WebElement myAccountButton;

}