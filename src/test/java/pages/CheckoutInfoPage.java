package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutInfoPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement zipCode;

    @FindBy(xpath = "//button[@id='cancel']")
    private WebElement cancelButton;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    public CheckoutInfoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFirstName(String firstName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameInput.sendKeys(lastName);
    }

    public void enterZipCode(String postalCode){
        zipCode.sendKeys(postalCode);
    }

    public void clickCancelButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

}
