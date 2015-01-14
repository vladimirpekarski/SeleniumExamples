package base;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;


public class ElementBase {
    protected SearchContext host;
    protected WebElement element;
    protected By locator;

    public ElementBase(SearchContext host, By locator) {
        this.host = host;
        this.locator = locator;
        element = host.findElement(locator);
    }


    public By getLocator() {
        return locator;
    }

    public SearchContext getHost() {
        return host;
    }

    public WebElement getWrappedElement() {
        return element;
    }

    public String getText() {
        return element.getText();
    }

    public void click() {
        element.click();
    }

    public void sendKeys(String valued) {
        element.sendKeys(valued);
    }
}
