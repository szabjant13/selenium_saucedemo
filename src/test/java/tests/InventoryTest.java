package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class InventoryTest extends BaseTest {

    @Test
    public void addBackpackToCartTest(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.loginFlow("standard_user","secret_sauce");
        inventoryPage.clickAddToCartBackpackButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertTrue(cartPage.isRemoveBackpackButtonVisible());
    }
}
