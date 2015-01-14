package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class CheckBox extends ElementBase {

    public CheckBox(SearchContext host, By locator) {
        super(host, locator);
    }

    public boolean isSelected() {
        return element.isSelected();
    }

    public void check() {
        setValue(true);
    }

    public void uncheck() {
        setValue(false);
    }

    private void setValue(boolean value) {
        if(isSelected() != value) {
            element.click();
        }
    }
}
