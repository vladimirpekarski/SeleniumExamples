package pageElements;

import base.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table extends ElementBase {
    private final By TR_LOCATOR = By.cssSelector("tbody>tr");
    private final By TH_LOCATOR = By.cssSelector("th");
    private final By TD_LOCATOR = By.cssSelector("td");

    public Table(SearchContext host, By locator) {
        super(host, locator);
    }

    public int getQuantityOfRows() {
        return element.findElements(TR_LOCATOR).size();
    }

    public int getQuantityOfColumn() {
        List<WebElement> rows = element.findElements(TR_LOCATOR);
        return rows.get(0).findElements(TD_LOCATOR).size();
    }

    public List<WebElement> getHeader() {
        return element.findElements(TH_LOCATOR);
    }

    public List<String> getHeaderAsString() {
        List<String> headersAsString = new ArrayList<>();

        for (WebElement element: getHeader()) {
            headersAsString.add(element.getText());
        }

        return headersAsString;
    }

    public List<List<WebElement>> getRows() {
        List<List<WebElement>> elemntsByRows = new ArrayList<>();
        List<WebElement> rows = element.findElements(TR_LOCATOR);

        for (WebElement webElement: rows) {
            elemntsByRows.add(webElement.findElements(TD_LOCATOR));
        }

        return elemntsByRows;
    }

    public List<List<String>> getRowsAsString() {
        List<List<String>> rowsAsString = new ArrayList<>();
        List<String> rowAsString = new ArrayList<>();

        for(List<WebElement> row: getRows()) {
            for(WebElement elementInRow: row) {
                rowAsString.add(elementInRow.getText());
            }
            rowsAsString.add(new ArrayList<String>(rowAsString));
            rowAsString.clear();
        }

        return rowsAsString;
    }


    public List<List<WebElement>> getColumns() {
        List<List<WebElement>> columns = new ArrayList<>();
        List<List<WebElement>> rows = getRows();

        for(int i = 0; i < getQuantityOfColumn(); i++) {
            List<WebElement> column = new ArrayList<>();
            for(List<WebElement> row: rows) {
                column.add(row.get(i));
            }
            columns.add(column);
        }

        return columns;
    }

    public List<List<String>> getColumnsAsString() {
        List<List<String>> columnsAsString = new ArrayList<>();
        List<String> columnAsString = new ArrayList<>();

        for(List<WebElement> column: getColumns()) {
            for(WebElement elementInColumn: column) {
                columnAsString.add(elementInColumn.getText());
            }
            columnsAsString.add(new ArrayList<String>(columnAsString));
            columnAsString.clear();
        }

        return columnsAsString;
    }

    public List<WebElement> getColumn(int i) {
        return getColumns().get(i);
    }

    public List<String> getColumnAsString(int i) {
        return getColumnsAsString().get(i);
    }

    public List<String> getRowAsString(int i) {
        return getRowsAsString().get(i);
    }

    public WebElement getCell(int row, int cell) {
        return getRows().get(row).get(cell);
    }

    public String getCellAsString(int row, int cell) {
        return getRowsAsString().get(row).get(cell);
    }
}
