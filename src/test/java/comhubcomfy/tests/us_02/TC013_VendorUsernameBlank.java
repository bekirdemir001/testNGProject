package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC013_VendorUsernameBlank {
    String testCaseID = "US01_TC013";
    private final String testName = "US01 || TC013-Vendor leaves username blank";
    private final String expectedResult = "Vendor shows 'Email' and 'Email Verification Code' is required and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorUsernameBlank(){







    }
}
