package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class P05_VendorAccountPage {
    public P05_VendorAccountPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//h1[.='Welcome to Allover Commerce!']")
    public WebElement vendorAccountText;

    @FindBy(xpath = "//*[.='Not right now']")
    public WebElement notRightNowButton;

    @FindBy(xpath = "//a[@class='wcfm_menu_item']")
    public WebElement logoutButton;

}