package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC021_VendorWrongVerificationCode {
    private final String testName = "US02 || TC021-Vendor enters wrong verification code";
    private final String expectedResult = "Vendor cannot register and show 'Email verification code invalid' warning message";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorWrongVerificationCode(){









    }
}