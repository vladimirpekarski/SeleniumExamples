package pageobjecttest.flowpage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import pageobjectpattern.flowpages.LoginPageFlow;

import java.util.concurrent.TimeUnit;

public class FlowPageTest {
    private static final String
            BASE_URL = "http://the-internet.herokuapp.com/login";
    private WebDriver driver;
    private LoginPageFlow loginPageFlow;

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

        loginPageFlow = new LoginPageFlow(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void pageObjectFlowTest() {

    }
}
