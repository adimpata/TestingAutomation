package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart extends Base{
    private static final By SHOP_PAGE = By.cssSelector("li[id='menu-item-40'] a");
    private static final By QTY = By.cssSelector("input[title='Qty']");
    private static final By BUTTON = By.cssSelector("button[type='submit']");
    public Cart(WebDriver driver) {
        super(driver);
    }
}
