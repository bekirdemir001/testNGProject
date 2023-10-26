package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC018_VendorWeakPassword {
    String testCaseID = "US01_TC018";
    private final String testName = "US01 || TC018-Vendor enters weak password";
    private final String expectedResult = "Vendor shows 'Password strength should be at least Good' warning message and cannot register";
    String actualResult = "Vendor couldn't register and showed warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorWeakPassword(){









    }
}