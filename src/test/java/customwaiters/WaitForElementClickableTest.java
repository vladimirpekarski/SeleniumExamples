package customwaiters;

import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class WaitForElementClickableTest {
    private static final String FIRST_SITE = "http://www.tut.by/";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("FireFox")String browser) {
        switch (browser) {
            case "FireFox": driver = new FirefoxDriver();
                break;
            case "Chrome": driver = new ChromeDriver();
                break;
            case "InternetExplorer": driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.get(FIRST_SITE);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END).perform();
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    private void waitElementIsClickablePositive() {
        driver.findElement(By.id("useransw")).click();

        WebElement clickableElement = Waiters.waitForElementClickable(
                driver, By.cssSelector("[name = 'send']"));
    }

    @Test
    private void waitElementIsClickableNegative() throws InterruptedException {
        WebElement clickableElement = Waiters.waitForElementClickable(
                driver, By.cssSelector("[name = 'send']"));
    }
}
