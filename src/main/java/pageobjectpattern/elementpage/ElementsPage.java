package pageobjectpattern.elementpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageElements.Button;
import pageElements.CheckBox;
import pageElements.InputField;
import pageElements.Label;

public class ElementsPage {
    private final By SEARCH_FIELD_LOCATOR = By.id("text");
    private final By FIND_BUTTON_LOCATOR =
            By.cssSelector(".b-line_search button");
    public final By FIND_TABLE_LOCATOR = By.cssSelector("table");
    private By OUTER_COMPUTER = By.cssSelector(".checkbox2__box label");
    private final By OUTER_COMPUTER_CHECKBOX = By
            .cssSelector(".domik2__dropdown-form input[type=checkbox]");


    public InputField searchfield;
    public Button findButton;
    public Label outerComputer;
    public CheckBox outerCheckbox;
    public WebDriver driver;

    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        searchfield = new InputField(driver, SEARCH_FIELD_LOCATOR);
        findButton = new Button(driver, FIND_BUTTON_LOCATOR);
//        outerComputer = new Label(driver, OUTER_COMPUTER);
//        outerCheckbox = new CheckBox(driver, OUTER_COMPUTER_CHECKBOX);
    }

    public void find(String value) {
        searchfield.sendKeys(value);
        findButton.click();
    }
}
