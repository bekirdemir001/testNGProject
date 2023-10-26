package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC014_VendorVerificationCodeBlank {
    String testCaseID = "US01_TC014";
    private final String testName = "US01 || TC014-Vendor leaves verification code blank";
    private final String expectedResult = "Vendor shows 'Email Verification Code' is required and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorVerificationCodeBlank(){









    }
}