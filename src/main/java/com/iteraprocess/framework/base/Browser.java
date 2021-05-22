package com.iteraprocess.framework.base;

import org.openqa.selenium.WebDriver;

public class Browser extends Base{
    public BrowserType Type;
    private WebDriver _driver;

    public Browser(WebDriver driver) {
        _driver = driver;
    }

    public void GoToUrl(String url) {
        System.out.println("["+url+"]");
        _driver.get(url);
//        _driver.manage().window().setSize(new Dimension("375","667"));
    }

    public void Maximize() {
        _driver.manage().window().maximize();
    }
}
