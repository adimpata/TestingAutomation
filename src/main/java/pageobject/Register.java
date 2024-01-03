package pageobject;

import config.Config;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
/**
 * The Register class represents a Page Object for handling user registration functionality.
 * It extends the {@code Base} class, which provides common functionality and configurations.
 */
public class Register extends Base {
    private Config config;

    public Register(WebDriver driver) {
        super(driver);
        this.config = new Config();
    }
    Configuration configuration = Base.getConfiguration();

    /**
     * Sets the username and password for the registration operation.
     */
    public void setUsernameAndPassword() {
        action.click(My_Account);
        String username = configuration.getValue("username");
        String password = configuration.getValue("password");
        action.type(getSelector("input_Username"), username);
        action.type(getSelector("input_Password"), password);
    }
    /**
     * Clicks the register button.
     */
    public void clickRegisterButton() {
        action.click(getSelector("registerButton"));
    }
}
