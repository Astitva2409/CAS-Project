package com.tests;

import java.io.IOException;

import org.testng.annotations.Test;
import com.base.BaseClass;

public class Tests extends BaseClass {
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
        printPrice("//div[@class='z4Ha90']/span/div/div/div[2]/span", 1);
        closeBrowser();
        switchWindowHome();
        clearText();
        enterText("q", "Laptops");
        elementClick("//button[@type='submit']");
        switchWindowProduct("//div[@class='_2kHMtA']", 5);
        verifyHomePage();
        displayOrderAmount("//div[@class='_30jeq3 _16Jk6d']");
        elementClick("//ul[@class='row']/li/button[text()='Add to cart']");
        elementClick("//ul[@class='row']/li/button[text()='Add to cart']");
        elementClick("//a[@class='_3SkBxJ']//span");
        takeScreenShot();
        verifyCart("//div[@class='zab8Yh _10k93p']");
        printPrice("//div[@class='z4Ha90']/span/div/div/div[2]/span", 2);
        closeBrowser();
        quitBrowser();
    }
}
