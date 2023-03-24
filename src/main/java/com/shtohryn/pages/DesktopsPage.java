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
    private WebElement displayDropdown;
    @FindBy(className="product-item")
    private WebElement numOfProducts;
    @FindBy(id="products-orderby")
    private WebElement sortDropdown;
    @FindBy(xpath = "//div[@class='prices']//span[@class='price actual-price']")
    private WebElement mostExpensiveItem;
    @FindBy(css="div.item-box")
    private List<WebElement> productBlocks;
    String button = "//input[@value='Add to cart']";
    @FindBy(css = "span.price.actual-price")
    private WebElement cartItem;


    public DesktopsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void setDisplayNumPerPage(String numPerPage)throws InterruptedException{
        LOG.info("Set Display to \"4\" per page");
        Select displayDropdownSelect = new Select(displayDropdown);
        displayDropdownSelect.selectByVisibleText("4");
        Thread.sleep(1);
    }
    public void sortBtPriceHighToLow(){
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
