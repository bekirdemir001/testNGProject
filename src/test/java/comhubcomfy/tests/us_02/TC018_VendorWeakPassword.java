package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC018_VendorWeakPassword {
    private final String testName = "US01 || TC018-Vendor enters weak password";
    private final String expectedResult = "Vendor shows 'Password strength should be at least Good' warning message and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void vendorWeakPassword(){









    }
}