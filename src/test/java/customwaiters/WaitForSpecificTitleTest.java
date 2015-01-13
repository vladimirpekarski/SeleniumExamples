package customwaiters;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

public class WaitForSpecificTitleTest {
    private static final String FIRST_SITE = "http://www.tut.by/";
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
    public void titleNegativeTest() {
        Waiters.waitForSpecificTilte(driver, "Xbox");
    }

    @Test
    public void titlePositeveTest() {
        Waiters.waitForSpecificTilte(driver, "Белорусский портал TUT.BY");
    }
}
