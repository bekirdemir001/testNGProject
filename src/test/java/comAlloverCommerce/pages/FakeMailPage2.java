package comAlloverCommerce.pages;

import comAlloverCommerce.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FakeMailPage2 {
    public FakeMailPage2(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//ul[@class='mailler']//li[2]//a")
    public WebElement firstMail;

    @FindBy(xpath = "//a[@class='link']")
    public WebElement resetPasswordLink;

    @FindBy(xpath = "//div[@id='body_content_inner']//p[2]")
    public WebElement verificationCode;

    @FindBy(xpath = "//div[@class='mail-oku-menu']//a")
    public WebElement backButton;

}
