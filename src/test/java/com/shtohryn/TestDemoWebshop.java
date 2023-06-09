package com.shtohryn;

import com.shtohryn.pages.BuildYourOwnExpensiveComputerPage;
import com.shtohryn.pages.DesktopsPage;
import com.shtohryn.pages.HomePage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDemoWebshop {
    private static WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(TestDemoWebshop.class);
    private static HomePage homePage;
    private static DesktopsPage desktopsPage;
    private static BuildYourOwnExpensiveComputerPage buildYourOwnExpensiveComputerPage;

    @BeforeAll
    static void setup() {
        LOG.info("-----------------------------------SETUP----------------------------------");
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        homePage = new HomePage(driver);
        desktopsPage = new DesktopsPage(driver);
        buildYourOwnExpensiveComputerPage = new BuildYourOwnExpensiveComputerPage(driver);
    }

    @Test
    @DisplayName("Add to cart the most expensive item")
    void testAddMostExpensiveItemToCart() throws InterruptedException {
        LOG.info("Proceed to website -> 'demowebshop'");
        driver.get("http://demowebshop.tricentis.com/");
        homePage.proceedToDestopsPage();
        desktopsPage.setDisplayNumPerPage("4");
        int numOfProducts = driver.findElements(By.className("product-item")).size();
        assertEquals(4, numOfProducts);
        LOG.info("Verify that only 4 items are displayed ");
        assertEquals(4, numOfProducts);
        desktopsPage.sortBtPriceHighToLow();
        desktopsPage.getMostExpensiveItem();
        WebElement cartItem = desktopsPage.addToCartFirstItem();
        LOG.info("Verify that the item is in the shopping cart.");
        assertTrue(cartItem.isDisplayed());
    }
    @Test
    @DisplayName("Add computer to cart and verify cart item")
    void testAddToCart() throws InterruptedException {
        buildYourOwnExpensiveComputerPage.setProcessorFast();
        buildYourOwnExpensiveComputerPage.setMaxRam();
        buildYourOwnExpensiveComputerPage.selectAllAvailableSoftware();
        String message = buildYourOwnExpensiveComputerPage.addToCart();
        String cartCount = buildYourOwnExpensiveComputerPage.getCartCount();
        WebElement cartItem = buildYourOwnExpensiveComputerPage.getCartItem();

        LOG.info("Verify that the alert message is 'The product has been added to your shopping cart'");
        assertEquals(message, "The product has been added to your shopping cart");

        LOG.info("Verify that Cart count  is '1'");
        assertEquals("(1)", cartCount);

        LOG.info("Verify that Cart item is displayed");
        assertTrue(cartItem.isDisplayed(), "Cart item should be displayed");

        buildYourOwnExpensiveComputerPage.removeItemFromShopCart();
    }
    @AfterAll
    static void tearDown() {
        driver.quit();
        LOG.info("-----------------------------------TEARDOWN----------------------------------");
    }
}