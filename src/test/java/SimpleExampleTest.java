import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SimpleExampleTest {
    private static final Logger LOG = Logger.getLogger(
            SimpleExampleTest.class);
    private static final String FIRST_SITE = "http://www.tut.by/";
    private static final String SECOND_SITE = "http://www.habrahabr.ru/";
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }

    @BeforeMethod
    public void setup() {
        try {
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();

            driver.get(FIRST_SITE);
            LOG.info("setup finished");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
            driver.close();
        }
    }

    @AfterMethod
    public void teardown() {
        driver.close();
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

            Assert.assertEquals(driver.getCurrentUrl().substring(0, 18),
                    FIRST_SITE);
        } catch (AssertionError e) {
            LOG.error("TEST checkingURLAfterBack FAILS: " + e.getMessage());
            Assert.fail();
        }
    }
}
