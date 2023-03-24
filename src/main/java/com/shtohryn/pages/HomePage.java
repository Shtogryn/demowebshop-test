package com.shtohryn.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage {
    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
    @FindBy(linkText = "Computers")
    private WebElement computersLink;
    @FindBy(linkText = "Desktops")
    private WebElement desktopsLink;


    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void proceedToDestopsPage() throws InterruptedException {
        LOG.info("Open Computer -> Desktops");
        computersLink.click();
        Thread.sleep(1);
        desktopsLink.click();
        Thread.sleep(1);
    }
}
