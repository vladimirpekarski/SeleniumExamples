package customwaiters;

import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class WaitForTextInElementPresentText {
    private static final String FIRST_SITE = "http://the-internet.herokuapp.com/";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("Chrome")String browser) {
        switch (browser) {
            case "FireFox": driver = new FirefoxDriver();
                break;
            case "Chrome": driver = new ChromeDriver();
                break;
            case "InternetExplorer": driver = new InternetExplorerDriver();
                break;
        }
        driver.manage().window().maximize();

        driver.get(FIRST_SITE);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void textPresentInElementPositiveTest() {
        WebElement textInSpecificElement = Waiters.waitForTextInElementPresent(
                driver, By.cssSelector("h1"), "Welcome to the Internet");
    }

    @Test
    public void textPresentInElementNegativeTest() {
        WebElement textInSpecificElement = Waiters.waitForTextInElementPresent(
                driver, By.cssSelector("h1"), "Welcome to Hell");
    }
}
