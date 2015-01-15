package customwaiters;

import helpers.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class WaitForHanfkesMoreThanTest {
    private static final String
            FIRST_SITE = "http://www.w3schools.com/html/html5_draganddrop.asp";
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

        driver.get(FIRST_SITE);
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void handlesMoreThanPositiveTest() {
        driver.findElement(By.cssSelector(".tryitbtn")).click();
        Waiters.waitForHandlesMoreThan(driver, 1);
    }

    @Test
    public void handlesMoreThanNegativeTest() {
        driver.findElement(By.cssSelector(".tryitbtn")).click();
        Waiters.waitForHandlesMoreThan(driver, 3);
    }
}
