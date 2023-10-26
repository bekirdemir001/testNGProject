package comhubcomfy.tests.us_02;

import org.testng.annotations.Test;

public class TC022_VendorWrongConfirmPassword {
    String testCaseID = "US01_TC022";
    private final String testName = "US01 || TC022-Vendor enters wrong password";
    private final String expectedResult = "Vendor cannot register and show 'Password and Confirm-password are not same' warning message";
    String actualResult = "Vendor cannot register and show 'Password and Confirm-password are not same' warning message";
    @Test(testName = testName, description = "<span style='font-weight:bold'>Expected Result: </span> " + expectedResult)
    public void vendorWrongConfirmPassword(){









    }
}