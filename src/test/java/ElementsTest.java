import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        LOG.info("checkboxesTest passed");
    }

    @Test
    public void dynamicControlsTest() {
        LOG.info("dynamicControlsTest starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/dynamic_controls']"));
            ref.click();

            WebElement buttonRemove = driver.findElement(By.id("btn"));
            WebElement radiobutton = driver.findElement(
                    By.cssSelector("input#checkbox"));
            WebElement textForRadiobutton = driver.findElement(
                    By.cssSelector("div#checkbox"));

            Assert.assertFalse(radiobutton.isSelected());
            Assert.assertEquals(buttonRemove.getText().trim(), "Remove");
            Assert.assertEquals(
                    textForRadiobutton.getText().trim(), "A checkbox");

            buttonRemove.click();
            WebDriverWait waitForChanges = new WebDriverWait(driver, 5);
            waitForChanges.until(ExpectedConditions.invisibilityOfElementLocated(
                    (By.cssSelector("div#checkbox"))));

            Assert.assertEquals(buttonRemove.getText().trim(), "Add");
            Assert.assertTrue(driver.findElement(By.id("message")).isDisplayed());

        } catch (AssertionError e) {
            LOG.error("TEST dynamicControlsTest FAILS: " + e.getMessage());
            Assert.fail("Assert fails");
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Timeout Exception");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Exception");
        }
        LOG.info("dynamicControlsTest passed");
    }

    @Test
    public void dropdownTest() {
        LOG.info("dropdownTest starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/dropdown']"));
            ref.click();

            Select dropdownList = new Select(
                    driver.findElement(By.id("dropdown")));

            Assert.assertFalse(dropdownList.isMultiple());

            Assert.assertEquals(3, dropdownList.getOptions().size());

            dropdownList.selectByVisibleText("Option 1");
            Assert.assertEquals("Option 1",
                    dropdownList.getFirstSelectedOption().getText());

            dropdownList.selectByValue("2");
            Assert.assertEquals("Option 2",
                    dropdownList.getFirstSelectedOption().getText());

        } catch (AssertionError e) {
            LOG.error("TEST dropdownTest FAILS: " + e.getMessage());
            Assert.fail("Assert fails");
        } catch (TimeoutException e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Timeout Exception");
        } catch (Exception e) {
            LOG.error(Arrays.toString(e.getStackTrace()).replaceAll(",","\n"));
            Assert.fail("Exception");
        }
        LOG.info("dropdownTest passed");
    }

    @Test
    public void loginFormTestPositive() {
        LOG.info("loginFormTestPositive starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/login']"));
            ref.click();

            WebElement userNameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement buttonLogin = driver.findElement(
                    By.cssSelector("button[type = 'submit']"));

            userNameField.sendKeys("tomsmith");
            passwordField.sendKeys("SuperSecretPassword!");
            buttonLogin.click();

            WebElement logOutButton = driver.findElement(By.cssSelector
                    ("a[href = '/logout']"));
            logOutButton.click();

            Assert.assertTrue(driver.getPageSource().contains("Login Page"));

        } catch (AssertionError e) {
            LOG.error("TEST loginFormTestPositive FAILS: " + e.getMessage());
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
        LOG.info("loginFormTestPositive passed");
    }

    @Test
    public void loginFormTestNegativeUserNameError() {
        LOG.info("loginFormTestNegativeUserNameError starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/login']"));
            ref.click();

            WebElement userNameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement buttonLogin = driver.findElement(
                    By.cssSelector("button[type = 'submit']"));

            userNameField.sendKeys("test");
            passwordField.sendKeys("SuperSecretPassword!");
            buttonLogin.click();

            WebDriverWait waitForError = new WebDriverWait(driver, 5);
            waitForError.until(ExpectedConditions.visibilityOf(
                    driver.findElement(
                            By.cssSelector("div[class = 'flash error']"))));

            Assert.assertTrue(
                    driver.getPageSource().contains("Your username is invalid!"));

        } catch (AssertionError e) {
            LOG.error("TEST loginFormTestNegativeUserNameError FAILS: " + e.getMessage());
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
        LOG.info("loginFormTestNegativeUserNameError passed");
    }

    @Test
    public void loginFormTestNegativePasswordError() {
        LOG.info("loginFormTestNegativePasswordError starts");
        try {
            WebElement ref = driver.findElement(
                    By.cssSelector("a[href='/login']"));
            ref.click();

            WebElement userNameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement buttonLogin = driver.findElement(
                    By.cssSelector("button[type = 'submit']"));

            userNameField.sendKeys("tomsmith");
            passwordField.sendKeys("test!");
            buttonLogin.click();

            WebDriverWait waitForError = new WebDriverWait(driver, 5);
            waitForError.until(ExpectedConditions.visibilityOf(
                    driver.findElement(
                            By.cssSelector("div[class = 'flash error']"))));

            Assert.assertTrue(
                    driver.getPageSource().contains("Your password is invalid!"));

        } catch (AssertionError e) {
            LOG.error("TEST loginFormTestNegativePasswordError FAILS: " + e.getMessage());
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
        LOG.info("loginFormTestNegativePasswordError passed");
    }
}
