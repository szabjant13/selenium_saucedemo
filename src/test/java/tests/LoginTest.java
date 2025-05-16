package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(inventoryPage.isProductTextVisible());
    }

    @Test
    public void loginWithWrongCredentialsTest(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("rossz_jelszo");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageFromErrorBox(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void loginWithBannedUserTest(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.enterUsername("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessageFromErrorBox(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
