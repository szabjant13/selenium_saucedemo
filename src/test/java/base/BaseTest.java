package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Collections;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "O:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        // 🔒 Letiltja a biztonsági értesítéseket, figyelmeztetéseket
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--incognito"); // vagy használhatsz clean user-data-dir-t

        // 🔧 Rejtse el az "Chrome is being controlled by automated software" üzenetet
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
