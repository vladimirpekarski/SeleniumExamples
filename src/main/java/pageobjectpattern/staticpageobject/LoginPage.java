package pageobjectpattern.staticpageobject;

import org.openqa.selenium.By;

public class LoginPage {
    public static final By usernameField = By.id("username");
    public static final By passwordField = By.id("password");
    public static final By loginButton =
            By.cssSelector("button[type = 'submit']");
}
