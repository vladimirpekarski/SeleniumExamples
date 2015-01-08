package interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class interactionsTestsHomeWork {
    private static final String BASE_URL = "http://the-internet.herokuapp.com";
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.get(BASE_URL);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void nestedFramesTest() {
        driver.findElement(By.cssSelector("a[href='/frames']")).click();
        driver.findElement(By.cssSelector("a[href='/nested_frames']")).click();

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        WebElement leftMsg = driver.findElement(By.cssSelector("body"));
        Assert.assertEquals("LEFT", leftMsg.getText());

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        WebElement middleMsg = driver.findElement(By.id("content"));
        Assert.assertEquals("MIDDLE", middleMsg.getText());

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        WebElement rightMsg = driver.findElement(By.cssSelector("body"));
        Assert.assertEquals("RIGHT", rightMsg.getText());

        driver.switchTo().parentFrame().switchTo().parentFrame();
        driver.switchTo().frame("frame-bottom");
        WebElement bottomMsg = driver.findElement(By.cssSelector("body"));
        Assert.assertEquals("BOTTOM", bottomMsg.getText());
    }

    @Test
    public void iFramesTest() {
        driver.findElement(By.cssSelector("a[href='/frames']")).click();
        driver.findElement(By.cssSelector("a[href='/iframe']")).click();

        driver.switchTo().frame("mce_0_ifr");
        WebElement textArea = driver.findElement(By.id("tinymce"));
        textArea.clear();

        textArea.sendKeys("Hello World");
        Assert.assertEquals("Hello World", textArea.getText());
    }
}