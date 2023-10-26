package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC021_VendorWrongVerificationCode {
    String testCaseID = "US01_TC021";
    private final String testName = "US01 || TC021-Vendor enters wrong verification code";
    private final String expectedResult = "Vendor cannot register and show 'Email verification code invalid' warning message";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorWrongVerificationCode(){









    }
}