package driver;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
/**
 * The Driver class provides methods for initializing and managing the WebDriver instance based on configuration settings.
 */
public class Driver {
    private static WebDriver driver;
    static Config config = Config.loadConfig("src/main/resources/config/config.json");
    /**
     * Retrieves the WebDriver instance. If the instance is not yet initialized, it will be initialized before returning.
     *
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }
    /**
     * Initializes the WebDriver based on the configuration settings.
     * Supports Firefox, Chrome, and Internet Explorer browsers.
     *
     * @throws RuntimeException If there is an error initializing the WebDriver.
     */
    private static void initializeDriver() {
        if (config != null) {
            try {
                switch (config.getBrowser().toLowerCase()) {
                    case "firefox":
                        System.setProperty("webdriver.gecko.driver", config.getFirefoxDriverPath());
                        driver = new FirefoxDriver();
                        break;
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
                        driver = new ChromeDriver();
                        break;
                    case "ie":
                        System.setProperty("webdriver.ie.driver", config.getInternetExplorerDriverPath());
                        driver = new InternetExplorerDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Browser " + config.getBrowser() + " is not supported.");
                }

                driver.manage().window().maximize();
            } catch (Exception e) {
                System.err.println("Error initializing WebDriver: " + e.getMessage());
                throw new RuntimeException("Unable to initialize WebDriver.", e);
            }
        } else {
            System.err.println("Error loading configuration.");
        }
    }
    /**
     * Quits the WebDriver instance if it is not null.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

