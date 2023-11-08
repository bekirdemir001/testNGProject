package comhubcomfy.tests.us_03;

import org.testng.annotations.Test;

public class TC022_UserUnRegisteredPassword {
    private final String testName = "US03 || TC022-User enters unregistered password";
    private final String expectedResult = "User cannot sign in and show 'Wrong username or password.' warning message";
    String actualResult = "User couldn't sign in and showed 'Wrong username or password.' warning message";

    @Test(testName = testName, description = "<span style='color:green; font-weight:bold; font-size: 16px'>Expected Result: </span> " + expectedResult)
    public void userUnRegisteredPassword(){






    }
}