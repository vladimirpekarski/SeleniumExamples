import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

public class SimpleExampleTest {
    private static final Logger LOG = Logger.getLogger(
            SimpleExampleTest.class);
    private static final String FIRST_SITE = "http://www.tut.by/";
    private static final String SECOND_SITE = "http://habrahabr.ru/";
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser) {
        try {
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();

            driver.get(FIRST_SITE);
            LOG.info("setup finished");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
            driver.quit();
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void simpleCheckingURL() {
        try {
            Assert.assertEquals(driver.getCurrentUrl().substring(0, 18),
                    FIRST_SITE);
        } catch (AssertionError e) {
            LOG.error("TEST simpleCheckingURL FAILS: " + e.getMessage());
            Assert.fail();
        }
    }

    @Test
    public void checkingURLAfterBack() {
        try {
            driver.get(SECOND_SITE);
            driver.navigate().back();

            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.titleContains("TUT.BY"));

            Assert.assertEquals(driver.getCurrentUrl().substring(0, 18),
                    FIRST_SITE);
        } catch (AssertionError e) {
            LOG.error("TEST checkingURLAfterBack FAILS: " + e.getMessage());
            Assert.fail();
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
        }
    }

    @Test
    public void checkingURLAfterForward() {
        try {
            driver.get(SECOND_SITE);
            driver.navigate().back();

            WebDriverWait waitFotTUT = new WebDriverWait(driver, 5);
            waitFotTUT.until(ExpectedConditions.titleContains("TUT.BY"));

            driver.navigate().forward();

            WebDriverWait waitForHabr = new WebDriverWait(driver, 5);
            waitForHabr.until(ExpectedConditions.titleContains("Хабрахабр"));

            Assert.assertEquals(driver.getCurrentUrl(), SECOND_SITE);
        } catch (AssertionError e) {
            LOG.error("TEST checkingURLAfterForward FAILS: " + e.getMessage());
            Assert.fail();
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
        }
    }
}
