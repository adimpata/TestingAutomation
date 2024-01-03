package pageobject;

import config.Config;
import configuration.Configuration;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Action;
import utilities.WaitUtils;

import java.io.IOException;

import static config.Config.getGlobalValue;
/**
 * The Base class serves as the base class for all Page Objects in the test framework.
 * It includes common functionality such as initializing WebDriver, performing actions, and handling configurations.
 */
public class Base extends Driver {
    protected WebDriver driver;
    protected Action action;
    private final WaitUtils waitUtils;
    private static Config config;
    private static Configuration configuration;
    protected final By My_Account = By.xpath("//a[normalize-space()='My Account']");
    protected final By messageWeb = By.cssSelector("button.fc-button.fc-cta-consent.fc-primary-button");
    public Base(WebDriver driver) {
        this.driver = driver;
        this.action = new Action(getDriver());
        this.waitUtils = new WaitUtils(getDriver());
    }
    /**
     * Initializes the Configuration instance for selector and value configurations.
     */
    static {
        try {
            configuration = new Configuration("src/main/resources/selectors.json", "src/main/resources/values.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Gets the Configuration instance for selector and value configurations.
     *
     * @return The Configuration instance.
     */
    public static Configuration getConfiguration() {
        return configuration;
    }
    /**
     * Opens the web page specified in the global configuration and clicks on a designated web element.
     */
    public void openAndClickTheWebPage() {
        waitUtils.setImplicitWait(10);
        String url = (String) getGlobalValue("url");
        if (url != null) {
            getDriver().get(url);
        } else {
            System.out.println("URL not found in global config");
        }
        action.click(messageWeb);
    }
    /**
     * Retrieves the By selector for the given key from the configuration.
     *
     * @param selectorKey The key corresponding to the selector in the configuration.
     * @return The By selector for the specified key.
     * @throws IllegalArgumentException If the selector format is invalid or the locator is not found for the key.
     */
    protected By getSelector(String selectorKey) {
        String selectorValue = null;
        try {
            selectorValue = configuration.getSelector(selectorKey);

            // Verifică tipul de selector și creează obiectul By corespunzător
            if (selectorValue.startsWith("id=")) {
                // Este un selector de tip ID
                return By.id(selectorValue.substring(3));
            } else if (selectorValue.startsWith("xpath=")) {
                // Este un selector de tip XPath
                return By.xpath(selectorValue);
            } else if (selectorValue.startsWith("name=")) {
                // Este un selector de tip Name
                return By.name(selectorValue.substring(5));
            } else if (selectorValue.startsWith("css=")) {
                // Este un selector de tip CSS
                return By.cssSelector(selectorValue.substring(4));
            } else {
                // Dacă nu îndeplinește nicio condiție, aruncăm o excepție
                throw new IllegalArgumentException("Invalid selector format for key: " + selectorValue);
            }
        } catch (Exception e) {
            // În caz de orice altă excepție, aruncăm o excepție cu mesajul corespunzător
            throw new IllegalArgumentException("Locator not found for key: " + selectorKey, e);
        }
    }
    /**
     * Closes the web page.
     */
    public void closePage() {
        //   quit();
    }
}
