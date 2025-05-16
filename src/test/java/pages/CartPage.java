package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    @FindBy(xpath = "//button[@id='remove-sauce-labs-backpack']")
    private WebElement removeBackPackButton;

    @FindBy(xpath = "//button[@id='continue-shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickRemoveBackPackButton() {
        removeBackPackButton.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public boolean isRemoveBackpackButtonVisible(){
        try {
            return removeBackPackButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
