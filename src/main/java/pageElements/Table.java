package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 14.01.15.
 */
public class Table extends ElementBase {
    public Table(SearchContext host, By locator) {
        super(host, locator);
    }

    public List<WebElement> getHeader() {
        return element.findElements(By.tagName("th"));
    }

    public List<String> getHeaderAsString() {
        List<String> headersAsString = new ArrayList<>();

        for (WebElement element: getHeader()) {
            headersAsString.add(element.getText());
        }

        return headersAsString;
    }

    public List<List<WebElement>> getRows() {

        List<List<WebElement>> allRows = new ArrayList<>();
        List<WebElement> rows = element.findElements(By.tagName("tr"));
        int countRows = element.findElements(By.tagName("tr")).size();

        for (int i = 0; i < countRows; i++) {

        }
        return new ArrayList<List<WebElement>>();
    }

    public List<List<String>> getRowsAsString() {
        return new ArrayList<List<String>>();
    }

    public List<WebElement> getRow(int i) {
        return new ArrayList<WebElement>();
    }

    public List<List<WebElement>> getColumns() {
        return new ArrayList<List<WebElement>>();
    }

    public List<List<String>> getColumnsAsString() {
        return new ArrayList<List<String>>();
    }

    public List<WebElement> getColumn(int i) {
        return new ArrayList<WebElement>();
    }

//    public WebElement getCell(int row, int cell) {
//        return ;
//    }



}
