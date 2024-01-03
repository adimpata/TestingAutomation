package JUnitTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import tests.WebPage;

import java.io.IOException;

public class WebPageTest {
    WebPage webPage;
    @Before
    public void open() throws IOException {
        webPage = new WebPage();
        webPage.openPage();
    }

    @Test
    public void register() {
        webPage.registerPage();
    }

    @Test
        public void login() {
        webPage.loginPage();
    }
    @Test
    public void shop() {
        webPage.shopPage();
    }

    @After
    public void close() {
        webPage.closePage();
    }
}

