package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class Button extends ElementBase {
    public Button(SearchContext host, By locator) {
        super(host, locator);
    }
}
