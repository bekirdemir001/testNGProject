package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FakeMailPage2 {
    public FakeMailPage2(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "eposta_adres")
    public WebElement emailText;

    @FindBy(xpath = "//ul[@class='mailler']//li[2]//a")
    public WebElement resetMail;

    @FindBy(xpath = "//a[@class='link']")
    public WebElement resetPasswordLink;

}
