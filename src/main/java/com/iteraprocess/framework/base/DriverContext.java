package com.iteraprocess.framework.base;

import com.iteraprocess.framework.utilities.CommonUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverContext {
    public static WebDriver Driver;
    public static Browser Browser;

    public static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    public  static WebElement WaitForElementVisible(final WebElement elementFindBy){
        WebDriverWait wait= new WebDriverWait(Driver, 60);
        try {
            return wait.until(ExpectedConditions.visibilityOf(elementFindBy));
        }catch(Exception e){
//            System.out.println("Don't found element "+e.getMessage());
            return null;
        }
    }

    public static void WaitElementEnabled(final By elementFindBy){
        WebDriverWait wait = new WebDriverWait(Driver,5);
        wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
    }
}
