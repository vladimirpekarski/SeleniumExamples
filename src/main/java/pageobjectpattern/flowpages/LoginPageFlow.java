package pageobjectpattern.flowpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by asus on 12.01.15.
 */
public class LoginPageFlow {
    WebDriver driver;
    private WebElement usernameField = driver.findElement(By.id("username"));
    private WebElement passwordField = driver.findElement(By.id("password"));
    private WebElement loginButton =  driver.findElement(
            By.cssSelector("button[type='submit']"));

    public LoginPageFlow(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageFlow typeUserName(String username) {
        usernameField.sendKeys("tomsmith");
        return this;
    }

}