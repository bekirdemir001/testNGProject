package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC015_VendorPasswordBlank {
    private final String testName = "US02 || TC015-Vendor leaves password blank";
    private final String expectedResult = "Vendor shows 'Password' is required and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorPasswordBlank(){









    }
}