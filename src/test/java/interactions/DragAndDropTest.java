package interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class DragAndDropTest {
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void dragAndDropTest() throws InterruptedException {
        WebElement ref = driver.findElement(
                By.cssSelector("a[href='/drag_and_drop']"));
        ref.click();
        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));

        Assert.assertEquals(columnA.getText(), "A");
        Assert.assertEquals(columnB.getText(), "B");

        Actions actions = new Actions(driver);
        actions.moveToElement(columnA).clickAndHold(columnA).moveToElement(columnB)
                .release().perform();

        Assert.assertEquals(columnA.getText(), "B");
        Assert.assertEquals(columnB.getText(), "A");

    }
}
