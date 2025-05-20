package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement orderDispatchedDiv;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backHomeButton;


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getOrderDispatchedText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(orderDispatchedDiv));
        return orderDispatchedDiv.getText();
    }
}
