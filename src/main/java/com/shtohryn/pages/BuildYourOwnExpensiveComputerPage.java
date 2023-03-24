package com.shtohryn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildYourOwnExpensiveComputerPage {
    private static final Logger LOG = LoggerFactory.getLogger(BuildYourOwnExpensiveComputerPage.class);
    @FindBy(id="product_attribute_74_5_26_82")
    private WebElement fastProcessorOption;
    @FindBy(xpath="//input[@id='product_attribute_74_6_27_85']")
    private WebElement ramOption;
    @FindBy(xpath="//input[@id='product_attribute_74_8_29_88']")
    private WebElement sofwareOption_1;
    @FindBy(xpath="//input[@id='product_attribute_74_8_29_89']")
    private WebElement sofwareOption_2;
    @FindBy(xpath="//input[@id='product_attribute_74_8_29_90']")
    private WebElement sofwareOption_3;
    @FindBy(css="input[value='Add to cart']")
    private WebElement addToCartButton;
    @FindBy(xpath="//*[@id=\"bar-notification\"]/p")
    private WebElement message;
    @FindBy(className="cart-qty")
    private WebElement cartCount;
    @FindBy(linkText="Shopping cart")
    private WebElement cartLink;
    @FindBy(xpath="//td[@class='product']//a[contains(@href, '/build-your-own-expensive-computer-2')]")
    private WebElement cartItem;
    @FindBy(name="removefromcart")
    private WebElement removeCartItemButton;
    @FindBy(xpath="//input[@name='updatecart']")
    private WebElement updateShoppingCartButtom;

    public BuildYourOwnExpensiveComputerPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    public void setProcessorFast() throws InterruptedException{
        LOG.info("");
        Thread.sleep(1000);
        fastProcessorOption.click();
        Thread.sleep(1000);
    }
    public void setMaxRam() throws InterruptedException{
        LOG.info("");
        Thread.sleep(1000);
        ramOption.click();
        Thread.sleep(1000);
    }
    public void selectAllAvailableSoftware() throws InterruptedException{
        LOG.info("");
        sofwareOption_1.click();
        Thread.sleep(1000);
        sofwareOption_2.click();
        Thread.sleep(1000);
        sofwareOption_3.click();
        Thread.sleep(1000);
    }
    public String addToCart() throws InterruptedException{
        LOG.info("");
        addToCartButton.click();
        Thread.sleep(500);
        return message.getText();
    }
    public String getCartCount(){
        LOG.info("");
        return cartCount.getText();
    }
    public WebElement getCartItem(){
        cartLink.click();
        LOG.info("");
        return cartItem;
    }
    public void removeItemFromShopCart() throws InterruptedException{
        LOG.info("");
        removeCartItemButton.click();
        Thread.sleep(1000);
        updateShoppingCartButtom.click();
        Thread.sleep(1000);
    }
}
