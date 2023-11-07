package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FakeMailPage {
    public FakeMailPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@class='animace']")
    public WebElement fakeMailAddress;

    @FindBy(xpath = "//tr[@class='hidden-xs hidden-sm klikaciRadek newMail']//td[2]")
    public WebElement verificationCodeEMail;

}
