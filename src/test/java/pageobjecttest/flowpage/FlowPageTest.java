package pageobjecttest.flowpage;


import base.BaseTestLoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjectpattern.flowpages.LoginPageFlow;

public class FlowPageTest extends BaseTestLoginPage {
    private LoginPageFlow loginPageFlow;

    @BeforeMethod
    @Override
    public void setup() {
        super.setup();
        loginPageFlow = new LoginPageFlow(driver);
    }

    @Test
    public void pageObjectFlowNegativeTest() throws InterruptedException {
        loginPageFlow.typeUserName("Bla")
                .typeUserPassword("bla")
                .clickLoginButton();

        Assert.assertTrue(loginPageFlow.errorMessageText()
                .contains("Your username is invalid!"));
    }
}
