package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC013_VendorUsernameBlank {
    private final String testName = "US01 || TC013-Vendor leaves username blank";
    private final String expectedResult = "Vendor shows 'Email' and 'Email Verification Code' is required and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorUsernameBlank(){







    }
}
