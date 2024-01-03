package pageobject;

import config.Config;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
/**
 * The Shop class represents the functionality related to shopping on the website.
 * It includes methods for writing reviews and adding products to the cart.
 */
public class Shop extends Base {
    private Config config;

    public Shop(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.config = new Config();
    }
    Configuration configuration = Base.getConfiguration();
    /**
     * This method is used to write a review for the product.
     */
    public void shopReview() {
        action.click(getSelector("shop_page"));
        action.scroll(getSelector("product"));
        action.click(getSelector("product"));
        action.click(getSelector("reviews"));
        action.click(getSelector("rating"));
        try {
            String comment = configuration.getValue("comment");
            String author = configuration.getValue("author");
            String email = configuration.getValue("username");

            action.type(getSelector("comment"), comment);
            action.type(getSelector("author"), author);
            action.type(getSelector("email"), email);

            try {
                if (!action.isSelected(getSelector("save_info"))) {
                    action.scroll(getSelector("save_info"));
                    action.click(getSelector("save_info"));
                }
                action.click(getSelector("shop_button"));
            } catch (Exception e) {
                System.out.println("An error occurred:    " + e.getMessage());
                e.printStackTrace();

            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * This method is used to add the product to the cart.
     */
    public void addProductToCart() {
        action.type(getSelector("qty"), "3");
        action.click(getSelector("shop_button"));
    }
}

