package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC020_VendorStrongPassword {
    String testCaseID = "US01_TC020";
    private final String testName = "US01 || TC020-Vendor enters strong password";
    private final String expectedResult = "Vendor registers successfully and shows 'Welcome to Hubcomfy!'";
    String actualResult = "Vendor couldn't register and showed 'Email verification code invalid' warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorStrongPassword(){









    }
}