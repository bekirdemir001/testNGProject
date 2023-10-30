package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC019_VendorGoodPassword {
    private final String testName = "US02 || TC019-Vendor enters good password";
    private final String expectedResult = "Vendor registers successfully and shows 'Welcome to Hubcomfy!'";
    String actualResult = "Vendor couldn't register and showed 'Email verification code invalid' warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorGoodPassword(){









    }
}