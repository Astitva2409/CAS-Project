package com.tests;

import java.io.IOException;

import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.Test;
import com.base.BaseClass;

public class Tests extends BaseClass {
    boolean result = false;
    int attempt = 0;
    @Test
    public void test() throws IOException {
        invokeBrowser();
        openURL("https://www.flipkart.com");
        verifyHomePage();
        elementClick("/html/body/div[2]/div/div/button");
        enterText("q", "Mobiles");
        elementClick("//button[@type='submit']");
        switchWindowProduct("//div[@class='_2kHMtA']", 0);
        verifyHomePage();
        displayOrderAmount("//div[@class='_30jeq3 _16Jk6d']");
        elementClick("//ul[@class='row']/li/button[text()='Add to cart']");
        takeScreenShot();
        printPrice("//div[@class='_1dqRvU']//span", 1);
        closeBrowser();
        switchWindowHome();
        clearText();
        enterText("q", "Laptops");
        elementClick("//button[@type='submit']");
        while (attempt < 2) {
            try {
                switchWindowProduct("//div[@class='_2kHMtA']", 5);
                result = true;
                break;
            } catch (StaleElementReferenceException e) {

            }
            attempt++;
        }
        verifyHomePage();
        displayOrderAmount("//div[@class='_30jeq3 _16Jk6d']");
        elementClick("//ul[@class='row']/li/button[text()='Add to cart']");
        elementClick("//ul[@class='row']/li/button[text()='Add to cart']");
        elementClick("//div[@class='go_DOp'][3]//a");
        takeScreenShot();
        verifyCart("//div[@class='zab8Yh _10k93p']");
        printPrice("//div[@class='_1dqRvU']//span", 2);
        closeBrowser();
        quitBrowser();
    }
}
