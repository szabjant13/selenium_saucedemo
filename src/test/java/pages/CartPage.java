package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }

    public void clickCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public boolean isRemoveBackpackButtonVisible(){
        try {
            return removeBackPackButton.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
