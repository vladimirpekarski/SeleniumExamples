import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

public class ElementsTest {
    private static final String BASE_URL = "\"http://the-internet.herokuapp.com";
    private static final Logger LOG = Logger.getLogger(
            NavigationTest.class);
    private WebDriver driver;

    @BeforeTest
    public void initLogger() {
        PropertyConfigurator.configure("./src/log4j.properties");
    }



}
