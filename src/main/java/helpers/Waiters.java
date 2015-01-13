package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    public static void waitForSpecificTilte(
            final WebDriver driver, final String title) {
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver1) {
                return (driver.getTitle().equals(title));
            }
        });
    }
}
