package pageElements;


import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;

public class InputField extends ElementBase{

    public InputField(SearchContext host, By locator) {
        super(host, locator);
    }

    @Override
    public String getText() {
        return element.getAttribute("value");
    }
}
