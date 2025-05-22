package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.TestDataHelper;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {
        test = extent.createTest("Successful Login Test", "Login with valid standard_user credentials");
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        driver.get(TestDataHelper.getData("url"));
        test.info("Entering credentials");
        loginPage.enterUsername(TestDataHelper.getData("validUsername"));
        loginPage.enterPassword(TestDataHelper.getData("validPassword"));
        test.info("Clicking login button");
        loginPage.clickLoginButton();
        test.info("Verifying product text visibility");
        Assert.assertTrue(inventoryPage.isProductTextVisible(), "Product text not visible — login might have failed");
        test.pass("Login successful and product text is visible");
    }

    @Test
    public void loginWithWrongCredentialsTest() {
        test = extent.createTest("Login With Wrong Credentials Test", "Login attempt with incorrect password");
        test.info("Navigating to login page");
        driver.get(TestDataHelper.getData("url"));
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering valid username and invalid password");
        loginPage.enterUsername(TestDataHelper.getData("invalidUsername"));
        loginPage.enterPassword(TestDataHelper.getData("validPassword"));
        test.info("Clicking login button");
        loginPage.clickLoginButton();
        String actualError = loginPage.getErrorMessageFromErrorBox();
        test.info("Verifying error message");
        try {
            Assert.assertEquals(actualError, TestDataHelper.getData("wrongCredentialsErrorMessage"));
            test.pass("Proper error message displayed");
        } catch (AssertionError e) {
            String screenshotPath = takeScreenshot("loginWithWrongCredentialsTest_failure");
            if (screenshotPath != null) {
                test.fail("Assertion failed, wrong error message was shown.").addScreenCaptureFromPath(screenshotPath);
            }
            throw e;
        }
    }

    @Test
    public void loginWithBannedUserTest() {
        test = extent.createTest("Login With Banned User Test", "Login attempt with locked out user");
        test.info("Navigating to login page");
        driver.get(TestDataHelper.getData("url"));
        LoginPage loginPage = new LoginPage(driver);
        test.info("Entering locked_out_user credentials");
        loginPage.enterUsername(TestDataHelper.getData("lockedUser"));
        loginPage.enterPassword(TestDataHelper.getData("validPassword"));
        test.info("Clicking login button");
        loginPage.clickLoginButton();
        String actualError = loginPage.getErrorMessageFromErrorBox();
        test.info("Verifying error message");
        try {
            Assert.assertEquals(actualError, TestDataHelper.getData("lockedOutUserErrorMessage"));
            test.pass("Proper error message displayed");
        } catch (AssertionError e) {
            String screenshotPath = takeScreenshot("loginWithlockedOutUserTest_failure");
            if (screenshotPath != null) {
                test.fail("Assertion failed, wrong error message was shown.").addScreenCaptureFromPath(screenshotPath);
            }
            throw e;
        }
    }
}
