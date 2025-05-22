package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartIcon;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement burgerMenuIcon;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement productsText;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpackButton;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickShoppingCartIcon(){
        shoppingCartIcon.click();
    }

    public void clickBurgerMenuIcon(){
        burgerMenuIcon.click();
    }

    public void clickAddToCartBackpackButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpackButton)).click();
    }

    public boolean isProductTextVisible(){
        try {
            return productsText.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
