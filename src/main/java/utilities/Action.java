package utilities;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
/**
 * The Action class provides utility methods for performing common actions on web elements using Selenium WebDriver.
 */
public class Action {
    private final WebDriver driver;
    private final WaitUtils waitUtils;
    public Action(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
    /**
     * Clears the value of the specified text field or text area and enters the specified text.
     *
     * @param locator the locator of the element to clear and enter text into
     * @param text    the text to enter into the element
     */
    public void type(By locator, String text) {
        waitUtils.waitForElementPresent(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
    /**
     * Clicks the element specified by the specified locator.
     *
     * @param locator the locator of the element to click
     */
    public void click(By locator) {
        waitUtils.waitForElementClickable(locator);
        driver.findElement(locator).click();
    }
    /**
     * Determines whether the element specified by the specified locator is selected.
     *
     * @param locator the locator of the element to check
     * @return true if the element is selected, false otherwise
     */
    public boolean isSelected(By locator) {
        waitUtils.waitForElementPresent(locator);
        return driver.findElement(locator).isSelected();
    }
    /**
     * Scrolls the page so that the element specified by the specified locator is visible in the viewport.
     *
     * @param locator the locator of the element to scroll to
     */
    public void scroll(By locator) {
        waitUtils.waitForElementPresent(locator);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
