package tests;

import org.openqa.selenium.WebDriver;
import pageobject.Login;
import pageobject.Register;
import pageobject.Shop;

import java.io.IOException;

public class WebPage {
    WebDriver driver;

    Register registerPage = new Register(driver);
    Login loginPage = new Login(driver);
    Shop shopPage = new Shop(driver);

    public WebPage() throws IOException {
    }


    public void openPage() {
        registerPage.openAndClickTheWebPage();
    }

    public void registerPage() {
        registerPage.setUsernameAndPassword();
        registerPage.clickRegisterButton();
    }
    public void loginPage(){
        loginPage.setUsernameAndPassword();
        loginPage.clickLoginButton();
    }
    public void shopPage(){
        shopPage.shopReview();
        shopPage.addProductToCart();
    }
    public void closePage(){
        registerPage.closePage();
    }
}
