package com.shtohryn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DesktopsPage {
    private static final Logger LOG = LoggerFactory.getLogger(DesktopsPage.class);
    @FindBy(name="products-pagesize")
    Select displayDropdown;
    @FindBy(className="product-item")
    int numOfProducts;
    @FindBy(id="products-orderby")
    WebElement sortDropdown;
    @FindBy(xpath = "//div[@class='prices']//span[@class='price actual-price']")
    WebElement mostExpensiveItem;
    @FindBy(css="div.item-box")
    List<WebElement> productBlocks;
    String button = "//input[@value='Add to cart']";
    @FindBy(css = "span.price.actual-price")
    WebElement cartItem;


    public DesktopsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public int setDisplayNumPerPage(String numPerPage)throws InterruptedException{
        LOG.info("Set Display to \"4\" per page");
        displayDropdown.selectByVisibleText(numPerPage);
        Thread.sleep(1);
        return numOfProducts;
    }
    public void sortBtPriceHightoLow(){
        LOG.info("Sort items \"Price: High to Low\"");
        Select sortSelect = new Select(sortDropdown);
        sortSelect.selectByVisibleText("Price: High to Low");
    }
    public void getMostExpensiveItem(){
        String itemName = mostExpensiveItem.getText();
        LOG.info("Most expensive item: {}", itemName);
    }
    public WebElement addToCartFirstItem() throws InterruptedException{
        LOG.info("add to cart item ");
        WebElement addToCartButton =productBlocks.get(0).findElement(By.xpath(button));
        addToCartButton.click();
        Thread.sleep(1);
        return cartItem;
    }
}
