package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

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

    @Test
    public void removeBackpackFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.loginFlow("standard_user","secret_sauce");
        inventoryPage.clickAddToCartBackpackButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertTrue(cartPage.isRemoveBackpackButtonVisible());
        cartPage.clickRemoveBackPackButton();
        cartPage.clickContinueShoppingButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertFalse(cartPage.isRemoveBackpackButtonVisible());
    }

    @Test
    public void buyBackpackTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver);
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        driver.get("https://www.saucedemo.com");
        loginPage.loginFlow("standard_user","secret_sauce");
        inventoryPage.clickAddToCartBackpackButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertTrue(cartPage.isRemoveBackpackButtonVisible());
        cartPage.clickCheckoutButton();
        checkoutInfoPage.enterFirstName("teszt");
        checkoutInfoPage.enterLastName("lastname");
        checkoutInfoPage.enterZipCode("1234");
        checkoutInfoPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
        System.out.println(checkoutCompletePage.getOrderDispatchedText());
        Assert.assertEquals(checkoutCompletePage.getOrderDispatchedText(),"Your order has been dispatched, and will arrive just as fast as the pony can get there!", "The text was not correctly shown");
    }
}
