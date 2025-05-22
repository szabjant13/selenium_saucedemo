package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import utils.TestDataHelper;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setUpSuite() {
        // ExtentReports konfigurálása
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Tesztadat betöltése
        TestDataHelper.loadTestData("src/test/data/testdata.json");
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "O:/chromedriver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--incognito");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void endTest() {
        extent.flush();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        // Végleges riport elmentése
        extent.flush();
    }

    public String takeScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // időbélyegző hozzáadása a fájlnévhez, hogy egyedi legyen
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = System.getProperty("user.dir") + "/test-output/" + screenshotName + "_" + timestamp + ".png";
            File destination = new File(dest);
            FileHandler.copy(source, destination);
            return dest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
