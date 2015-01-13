package customwaiters;

import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class WaitForAnyAlertTest {
    private static final String
            FIRST_SITE = "http://the-internet.herokuapp.com/javascript_alerts";
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
    public void anyAlertPositiveTest() {
        driver.findElement(By.cssSelector("button[onclick='jsAlert()']"))
                .click();
        Waiters.waitForAnyAlert(driver);
    }

    @Test
    public void anyAlertNegativeTest() {
        driver.findElement(By.cssSelector("button[onclick='false']"))
                .click();
        Waiters.waitForAnyAlert(driver);
    }
}
