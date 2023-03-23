package com.shtohryn;

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
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestDemoWebshop {
    private static WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(TestDemoWebshop.class);
    private static HomePage homePage;
    private static DesktopsPage desktopsPage;

    @BeforeAll
    static void setup() {
        LOG.info("-----------------------------------SETUP----------------------------------");
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        homePage = new HomePage(driver);
        desktopsPage = new DesktopsPage(driver);
    }

    @Test
    void testAddMostExpensiveItemToCart() {
        LOG.info("Proceed to website -> 'demowebshop'");
        driver.get("http://demowebshop.tricentis.com/");
        WebElement computersLink = driver.findElement(By.linkText("Computers"));
        computersLink.click();
        LOG.info("Open Computer -> Desktopsopen Computer -> Desktops");
        WebElement desktopsLink = driver.findElement(By.linkText("Desktops"));
        desktopsLink.click();

        Select displayDropdown = new Select(driver.findElement(By.name("products-pagesize")));
        displayDropdown.selectByVisibleText("4");

        int numOfProducts = driver.findElements(By.className("product-item")).size();
        assertEquals(4, numOfProducts);

        WebElement sortDropdown = driver.findElement(By.id("products-orderby"));
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price: High to Low");

        WebElement mostExpensiveItem = driver.findElement(By.xpath("//div[@class='prices']//span[@class='price actual-price']"));
        String itemName = mostExpensiveItem.getText();
        LOG.info("Most expensive item: {}", itemName);
        List<WebElement> productBlocks = driver.findElements(By.cssSelector("div.item-box"));
        WebElement addToCartButton = productBlocks.get(0).findElement(By.xpath("//input[@value='Add to cart']"));
        addToCartButton.click();

        WebElement cartItem = driver.findElement(By.cssSelector("span.price.actual-price"));
        assertTrue(cartItem.isDisplayed());
    }
    @Test
    @DisplayName("Add computer to cart and verify cart item")
    void testAddToCart() throws InterruptedException {
        Thread.sleep(1000);
        WebElement fastProcessorOption = driver.findElement(By.id("product_attribute_74_5_26_82"));
        fastProcessorOption.click();
        Thread.sleep(1000);

        WebElement ramOption = driver.findElement(By.xpath("//input[@id='product_attribute_74_6_27_85']"));
        ramOption.click();
        Thread.sleep(1000);

        WebElement sofwareOption = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_88']"));
        sofwareOption.click();
        Thread.sleep(1000);
        sofwareOption = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_89']"));
        sofwareOption.click();
        Thread.sleep(1000);
        sofwareOption = driver.findElement(By.xpath("//input[@id='product_attribute_74_8_29_90']"));
        sofwareOption.click();
        Thread.sleep(1000);

        WebElement addToCartButton = driver.findElement(By.cssSelector("input[value='Add to cart']"));
        addToCartButton.click();
        Thread.sleep(500);
        WebElement message = driver.findElement(By.xpath("//*[@id=\"bar-notification\"]/p"));
        LOG.info(message.getText());
        LOG.info("Verify that the alert message is 'The product has been added to your shopping cart'");
        assertEquals(message.getText(), "The product has been added to your shopping cart");

        WebElement cartCount = driver.findElement(By.className("cart-qty"));
        LOG.info("Verify that Cart count  is '1'");
        assertEquals("(1)", cartCount.getText());

        WebElement cartLink = driver.findElement(By.linkText("Shopping cart"));
        cartLink.click();
        WebElement cartItem = driver.findElement(By.xpath("//td[@class='product']//a[contains(@href, '/build-your-own-expensive-computer-2')]"));
        assertTrue(cartItem.isDisplayed(), "Cart item should be displayed");

        WebElement removeCartItemButton = driver.findElement(By.name("removefromcart"));
        removeCartItemButton.click();
        Thread.sleep(1000);

        WebElement updateShoppingCartButtom = driver.findElement(By.xpath("//input[@name='updatecart']"));
        updateShoppingCartButtom.click();
        Thread.sleep(1000);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
        LOG.info("-----------------------------------TEARDOWN----------------------------------");
    }
}//*/
