package pageobjectpattern.flowpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 12.01.15.
 */
public class LoginPageFlow {
    private WebDriver driver;
    private final By USERNAME_FIELD_LOCATOR = By.id("username");
    private By PASSWORD_FILED_LOCATOR = By.id("password");
    private By LOGIN_BUTTOM_LOCATOR = By.cssSelector("button[type='submit']");
    private By ERROR_MESSAGE_LOCATOR = By.id("flash");
    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement loginButton;
    private WebElement errorMessage;

    public LoginPageFlow(WebDriver driver) {
        this.driver = driver;
        usernameField = driver.findElement(USERNAME_FIELD_LOCATOR);
        passwordField = driver.findElement(PASSWORD_FILED_LOCATOR);
        loginButton =  driver.findElement(LOGIN_BUTTOM_LOCATOR);
    }

    public LoginPageFlow typeUserName(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPageFlow typeUserPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public String errorMessageText() {
        errorMessage = driver.findElement(ERROR_MESSAGE_LOCATOR);
        return errorMessage.getText();
    }
}