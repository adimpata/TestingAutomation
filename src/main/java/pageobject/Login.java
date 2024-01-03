package pageobject;

import config.Config;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
/**
 * The Login class represents a Page Object for handling login functionality.
 * It extends the Base class, which provides common functionality and configurations.
 */
public class Login extends Base {
    private Config config;
    public Login(WebDriver driver) throws IOException {
        super(driver);
        this.driver = driver;
        this.config = new Config();

    }
    /**
     * Returns the configuration object
     */
    Configuration configuration = Base.getConfiguration();
    /**
     * This function sets the username and password in the login form
     */
    public void setUsernameAndPassword() {
        try {
            action.click(My_Account);
            String username = configuration.getValue("username");
            String password = configuration.getValue("password");

            action.type(getSelector("inputUsername"), username);
            action.type(getSelector("inputPassword"), password);
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log or throw a custom exception)
        }
    }
    /**
     * This function clicks on the login button
     */
    public void clickLoginButton() {
        try {
            if (!action.isSelected(getSelector("rememberMeCheckbox"))) {
                action.click(getSelector("rememberMeCheckbox"));
            }
            action.click(getSelector("loginButton"));
        } catch (Exception e) {
            // Handle exception appropriately (e.g., log or
        }
    }
}