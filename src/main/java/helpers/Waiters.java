package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    public static void waitForSpecificTilte(
            WebDriver driver, final String title) {
        (new WebDriverWait(driver, 9)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return (title.equals(d.getTitle()));
            }
        });
    }
}
