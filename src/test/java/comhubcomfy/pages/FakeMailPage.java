package comhubcomfy.pages;

import comhubcomfy.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FakeMailPage {
    public FakeMailPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "1//*[@class='animace']")
    public WebElement fakeMailAddress;

}
