package com.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;

import com.utils.DateUtils;
import org.testng.Assert;

public class BaseClass {
    public WebDriver driver;
    public String currentHandle = null;
    public String newHandle = null;
    public List<WebElement> list;
    public WebElement query;

    public void invokeBrowser() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void openURL(String url) {
        driver.get(url);

    }

    public void quitBrowser() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        driver.quit();
    }

    public void closeBrowser() {
        driver.close();
    }

    public void verifyHomePage() {
        String pageTitle = driver.getTitle();
        System.out.print("The page title is: ");
        System.out.println(pageTitle);
        Assert.assertEquals(pageTitle, driver.getTitle());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void elementClick(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
        try {
            Thread.sleep(5000);
        } catch (NoSuchWindowException e) {

        } catch (InterruptedException e) {

		}
    }

    public void enterText(String name, String data) {
        query = driver.findElement(By.name(name));
        query.sendKeys(data);
    }

    public void switchWindowProduct(String xpath, int id) {
        currentHandle = driver.getWindowHandle();

        list = driver.findElements(By.xpath(xpath));
        System.out.println("Size is " + list.size());
        list.get(id).click();

        Set<String> set = driver.getWindowHandles();
        for (String s : set) {
            if (!s.equals(currentHandle)) {
                newHandle = s;
                driver.switchTo().window(newHandle);
            }
        }
    }

    public void printPrice(String xpath, int id) {
        if (id == 1) {
            String price = driver.findElement(By.xpath(xpath)).getText();
            System.out.println("Price after first product adding is: " + price);
        } else {
            String price = driver.findElement(By.xpath(xpath)).getText();
            System.out.println("Price after second product adding is: " + price);
        }
    }

    public void switchWindowHome() {
        driver.switchTo().window(currentHandle);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void clearText() {
        query.sendKeys(Keys.CONTROL + "a");
        query.sendKeys(Keys.DELETE);
    }

    public void displayOrderAmount(String xpath) {
        String orderAmount = driver.findElement(By.xpath(xpath)).getText();
        System.out.println(orderAmount);
    }

    public void takeScreenShot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
        String fileName = DateUtils.getTime();
        String destPath = System.getProperty("user.dir") + "//screenshots//" + fileName + ".png";
        try {
            FileUtils.copyFile(srcfile, new File(destPath));
            System.out.println("Saved successfully");
        } catch (Exception e) {
            System.out.println("Failed to save ss");
        }
    }

    public void verifyCart(String xpath) {
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        if (list.size() == 2) {
            System.out.println("The cart has two products");
        } else {
            System.out.println("Error");
        }
    }
}
