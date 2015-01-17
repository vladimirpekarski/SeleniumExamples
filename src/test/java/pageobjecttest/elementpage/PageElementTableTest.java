package pageobjecttest.elementpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjectpattern.elementpage.ElementPageTable;

import java.util.concurrent.TimeUnit;

public class PageElementTableTest {
    private static final String
            BASE_URL = "http://the-internet.herokuapp.com/challenging_dom";
    private WebDriver driver;
    private ElementPageTable pageWithTable;

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
        pageWithTable = new ElementPageTable(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @Test
    public void headerTest() {
        Assert.assertEquals(pageWithTable.getHeader(5), "Diceret");
    }

//    @Test
//    public void printTableByRows() {
//        pageWithTable.printTableByRows();
//    }
//
//    @Test
//    public void printTableByColumn() {
//        pageWithTable.printTableByColumn();
//    }

    @Test
    public void cellTest() {
        Assert.assertEquals("Definiebas4", pageWithTable.getCell(4, 3));
    }
}
