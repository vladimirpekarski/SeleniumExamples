package pageobjectpattern.elementpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageElements.Table;

import java.util.List;

public class ElementPageTable {

    private final By TABLE_LOCATOR = By.tagName("table");

    private Table table;
    private WebDriver driver;

    public ElementPageTable(WebDriver driver) {
        this.driver = driver;
        table = new Table(driver, TABLE_LOCATOR);
    }

    public void printTableSize() {
        System.out.println(
                table.getQuantityOfColumn() + " " + table.getQuantityOfRows());
    }

    public String getHeader(int index) {
        return table.getHeaderAsString().get(index);
    }

    public void printTableByRows() {
        for (List<String> row: table.getRowsAsString()) {
            for (String value: row) {
                System.out.print(value + "  ");
            }

            System.out.println("");
        }
    }

    public void printTableByColumn() {
        for(List<String> column: table.getColumnsAsString()) {
            for (String value: column) {
                System.out.println(value);
            }
        }
    }

    public String getCell(int row, int cell) {
        return table.getCellAsString(row, cell);
    }
}
