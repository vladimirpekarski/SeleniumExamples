import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SimpleExampleTest {
    private static final String FIRST_SITE = "http://www.tut.by";
    private static final String SECOND_SITE = "http://www.habrahabr.ru/";
    private WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get(FIRST_SITE);
        Thread.sleep(3000);
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }

    @Test
    public void getTest() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentUrl().contains(FIRST_SITE));
    }
}
