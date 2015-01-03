import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;

public class ElementsTest {
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
    private static final Logger LOG = Logger.getLogger(
            NavigationTest.class);
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("Chrome")String browser) {
        LOG.info("setup starts for " + browser);
        try {
            switch (browser) {
                case "FireFox": driver = new FirefoxDriver();
                    break;
                case "Chrome": driver = new ChromeDriver();
                    break;
                case "InternetExplorer": driver = new InternetExplorerDriver();
                    break;
            }
            driver.manage().window().maximize();

            driver.get(BASE_URL);
        } catch (WebDriverException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail();
            driver.quit();
        }
        LOG.info("setup finished for " + browser);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkboxesTest() {
        LOG.info("checkboxesTest starts");
        try {
            WebElement ref = driver.findElement(By.cssSelector("a[href='/checkboxes']"));
            ref.click();

            WebDriverWait waitForHabr = new WebDriverWait(driver, 5);
            waitForHabr.until(ExpectedConditions.titleContains("The Internet"));

            List<WebElement> checkboxes = driver.findElements(
                    By.cssSelector("input[type=checkbox]"));

            for (WebElement checkbox: checkboxes) {
                checkbox.click();
            }

            Assert.assertTrue(checkboxes.get(0).isSelected());
            Assert.assertFalse(checkboxes.get(1).isSelected());

        } catch (AssertionError e) {
            LOG.error("TEST checkboxesTest FAILS: " + e.getMessage());
            Assert.fail("Assert fails");
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Timeout Exception");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Exception");
        }
    }
}
