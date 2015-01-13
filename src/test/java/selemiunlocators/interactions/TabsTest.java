package selemiunlocators.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TabsTest {
    private static final String
            BASE_URL = "http://www.w3schools.com/html/html5_draganddrop.asp";
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(@Optional("InternetExplorer")String browser) {
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
    public void tabsTests() throws InterruptedException {
        WebElement tryYourSelfButton = driver.findElement(
                By.cssSelector(".tryitbtn"));

        Assert.assertEquals(driver.getWindowHandles().size(), 1);
        tryYourSelfButton.click();

        Assert.assertEquals(driver.getWindowHandles().size(), 2);
        ArrayList<String> handles = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(handles.get(0));
        Assert.assertEquals("HTML5 Drag and Drop", driver.getTitle());


        driver.switchTo().window(handles.get(1));
        Assert.assertEquals("Tryit Editor v2.3", driver.getTitle());

        driver.close();
        Assert.assertEquals(driver.getWindowHandles().size(), 1);
    }
}
