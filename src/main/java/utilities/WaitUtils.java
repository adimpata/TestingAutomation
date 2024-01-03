package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
/**
 * The WaitUtils class provides utility methods for handling waits in Selenium WebDriver.
 */
public class WaitUtils {
    private final WebDriver driver;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }
    /**
     * Sets the implicit wait timeout for this WebDriver instance.
     *
     * @param seconds the number of seconds to wait
     */
    public void setImplicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

    }
    /**
     * Waits for an element to be present on the page.
     *
     * @param locator the locator used to find the element
     * @return the found element
     */
    public WebElement waitForElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    /**
     * Waits for an element to be clickable.
     *
     * @param locator the locator used to find the element
     */
    public void waitForElementClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.err.println("The element did not become clickable while waiting.");
            throw e;
        }
    }
}
