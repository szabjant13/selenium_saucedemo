package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.TestDataHelper;

public class InventoryTest extends BaseTest {

    @Test
    public void addBackpackToCartTest(){
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        driver.get(TestDataHelper.getData("url"));
        loginPage.loginFlow(TestDataHelper.getData("validUsername"),TestDataHelper.getData("validPassword"));
        inventoryPage.clickAddToCartBackpackButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertTrue(cartPage.isRemoveBackpackButtonVisible());
    }

    @Test
    public void removeBackpackFromCartTest() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        driver.get(TestDataHelper.getData("url"));
        loginPage.loginFlow(TestDataHelper.getData("validUsername"),TestDataHelper.getData("validPassword"));
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
        driver.get(TestDataHelper.getData("url"));
        loginPage.loginFlow(TestDataHelper.getData("validUsername"),TestDataHelper.getData("validPassword"));
        inventoryPage.clickAddToCartBackpackButton();
        inventoryPage.clickShoppingCartIcon();
        Assert.assertTrue(cartPage.isRemoveBackpackButtonVisible());
        cartPage.clickCheckoutButton();
        checkoutInfoPage.enterFirstName(TestDataHelper.getData("orderFirstname"));
        checkoutInfoPage.enterLastName(TestDataHelper.getData("orderLastName"));
        checkoutInfoPage.enterZipCode(TestDataHelper.getData("zipCode"));
        checkoutInfoPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
        Assert.assertEquals(checkoutCompletePage.getOrderDispatchedText(), TestDataHelper.getData("orderDispatchedMessage"), "The text was not correctly shown");
    }
}
