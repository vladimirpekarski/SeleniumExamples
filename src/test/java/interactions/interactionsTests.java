package interactions;

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

public class interactionsTests {
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
    private static final Logger LOG = Logger.getLogger(
            interactionsTests.class);
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("FireFox")String browser) {
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

    @Test
    public void contentMenu() {
        LOG.info("contentMenu starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/context_menu']"));

            ref.click();
            Actions actions = new Actions(driver);
            WebElement box = driver.findElement(By.id("hot-spot"));

            actions.moveToElement(box).contextClick().
                    sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).
                        sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).
                            sendKeys(Keys.DOWN).sendKeys(Keys.ENTER).perform();

            Thread.sleep(3000);

            Alert promt = driver.switchTo().alert();
            Assert.assertEquals(promt.getText(), "You selected a context menu");

            promt.accept();

            Assert.assertFalse(isAlertPresent(driver));

        } catch (AssertionError e) {
            LOG.error("TEST contentMenu FAILS: " + e.getMessage());
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
        LOG.info("contentMenu passed");
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
