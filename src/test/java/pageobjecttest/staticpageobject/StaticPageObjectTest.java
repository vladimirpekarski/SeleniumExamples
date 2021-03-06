package pageobjecttest.staticpageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjectpattern.staticpageobject.LoginPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by asus on 12.01.15.
 */
public class StaticPageObjectTest {
    private static final String
            BASE_URL = "http://the-internet.herokuapp.com/login";
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
    public void pageObjectStaticLoginTest() {
        WebElement usernameField = driver.findElement(LoginPage.usernameField);
        WebElement passwordField = driver.findElement(LoginPage.passwordField);
        WebElement loginButton = driver.findElement(LoginPage.loginButton);

        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();

        Assert.assertTrue(driver.getPageSource().contains("Secure Area"));
    }
}
