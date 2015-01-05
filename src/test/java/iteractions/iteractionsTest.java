package iteractions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class iteractionsTest {
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
    private static final Logger LOG = Logger.getLogger(
            iteractionsTest.class);
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("InternetExplorer")String browser) {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void keyPressesTest() {
        LOG.info("keyPressesTest starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/key_presses']"));
            String letters = "ABCDEFGHIKLMOPQRSTXYZVJUW";
            ref.click();

            Actions actions = new Actions(driver);
            WebElement container = driver.findElement(By.id("result"));

            for(int i = 0; i < letters.length(); i++) {
                actions.sendKeys(String.valueOf(letters.charAt(i))).perform();
                Assert.assertTrue(container.getText().contains(
                        String.valueOf(letters.charAt(i))));
            }

        } catch (AssertionError e) {
            LOG.error("TEST keyPressesTest FAILS: " + e.getMessage());
            Assert.fail("Assert fails");
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Timeout Exception");
        } catch (ElementNotFoundException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Element Not Found");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Exception");
        }
        LOG.info("keyPressesTest passed");
    }

    @Test
    public void hoverTest() {
        LOG.info("hoverTest starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/hovers']"));

            ref.click();

            Actions actions = new Actions(driver);
            List<WebElement> divs = driver.findElements(
                    By.cssSelector("div.figure"));

            for (WebElement webElement: divs) {
                actions.moveToElement(webElement).perform();
                Assert.assertTrue(webElement.findElement(By.cssSelector("h5")
                ).isDisplayed());
                Thread.sleep(3000);
            }

        } catch (AssertionError e) {
            LOG.error("TEST hoverTest FAILS: " + e.getMessage());
            Assert.fail("Assert fails");
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Timeout Exception");
        } catch (ElementNotFoundException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Element Not Found");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Exception");
        }
        LOG.info("hoverTest passed");
    }

}
