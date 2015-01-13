package helpers;

import org.openqa.selenium.NoAlertPresentException;
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

    public static void waitForSpecificAlert(
            WebDriver driver, final String alertText) {
        (new WebDriverWait(driver, 9)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return (alertText.equals(d.switchTo().alert().getText()));
            }
        });
    }

    public static void waitForAnyAlert(
            WebDriver driver) {
        (new WebDriverWait(driver, 9)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                try {
                    d.switchTo().alert();
                    return true;
                }
                catch (NoAlertPresentException e) {
                    return false;
                }
            }
        });
    }
}
