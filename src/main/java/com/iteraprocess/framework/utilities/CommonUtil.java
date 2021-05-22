package com.iteraprocess.framework.utilities;

import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.UnhandledException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import com.iteraprocess.framework.base.DriverContext;
import com.iteraprocess.framework.config.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CommonUtil {

    public static void selectList(WebElement element, String value){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(UnknownError.class).ignoring(WebDriverException.class);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
        }
        if (DriverContext.Driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverContext.Driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        Select cmb = new Select(element);
        cmb.selectByVisibleText(value);
    }

    public static void selectListItem(WebElement element, int value) {
        Select cmb = new Select(element);

//        cmb.selectByVisibleText(value);
        cmb.selectByIndex(value);
        CommonUtil.waitSleep(3000);
        CommonUtil.waitPageLoad();
    }

    public static void selectListByIndex(WebElement element, int index) {

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(UnknownError.class).ignoring(WebDriverException.class);
        try {

            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
        }

        if (DriverContext.Driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverContext.Driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }

        Select cmb = new Select(element);
        cmb.selectByIndex(index);

//        waitForPageLoaded();
    }

    public static WebElement waitElementVisible(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(UnknownError.class).ignoring(WebDriverException.class);

        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
            return null;
        }
    }

    public static WebElement waitElementToClick(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(UnknownError.class).ignoring(WebDriverException.class);
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.out.println("Don't found element " + e.getMessage());
            return null;
        }
    }

    public static void waitForPageLoaded() {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(UnknownError.class).ignoring(WebDriverException.class);
        try {
            wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            System.out.println("Don't load the page " + e.getMessage());
        }
    }

    public static void waitPageLoad() {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(10, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(UnhandledException.class).ignoring(UnknownError.class);
        try {
            wait.until(ExpectedConditions.visibilityOf((DriverContext.Driver.findElement(By.id("top_frame")))));
        }catch(Exception e){
            //System.out.println("Don't load the page "+e.getMessage());
        }
    }

    public static void sendChar(WebElement element, String value) {
        element.clear();

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            String s = new StringBuilder().append(c).toString();
            element.sendKeys(s);
        }
    }

    public static void waitForEnabled(WebElement element) {
        new WebDriverWait(DriverContext.Driver, 30).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForNotVisibility(By element) {
        int retry = 90;
        int intTimeWait = 1000;

        for (int i = 0; i < retry; i++) {
            try {
                DriverContext.Driver.findElement(element);
                waitSleep(intTimeWait);
            } catch (Exception e) {
                break;
            }
        }
    }

    public static void waitSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<WebElement> getListWebElements(By byElement) {
        List<WebElement> weList = null;
        try {
            weList = DriverContext.Driver.findElements(byElement);
        } catch (Exception e) {
            System.out.println("Don't found element [" + byElement + "]");
        }
        return weList;
    }

    public static String randomNumber(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        return result + "";
    }

    public static void ScrollIntoView(WebElement element) {
        ((JavascriptExecutor) DriverContext.Driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void moveMouseToElement() {
        DriverContext.Driver.findElement(By.xpath("//ck-button[text()='Cotizar']")).click();
        new Actions(DriverContext.Driver).moveToElement(DriverContext.Driver.findElement(By.xpath("//ck-button[text()='Cotizar']"))).click().perform();
    }

    public static void removeBackspaceInput(WebElement element, int nroRetry) {
        for (int i = 0; i < nroRetry; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        if (element.getAttribute("value").length() > 0) {
            for (int i = 0; i < nroRetry; i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    public static String getExecutiveTime(long endMillis, long startMillis) {

        long millis = endMillis - startMillis;
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
    }

    public static String getFeatureName(String id) {
        String[] strName = id.split(";");
        String output = strName[0].substring(0, 1).toUpperCase() + strName[0].substring(1);
        return output.replace('-', ' ');
    }

    public static void takeScreenshot(WebDriver driver, Scenario scenario) {

        try {
            File dir = new File(Settings.ScreenPath);
            if (!dir.exists())
                dir.mkdir();
        } catch (Exception e) {
        }

        File scrFile = ((TakesScreenshot) DriverContext.Driver).getScreenshotAs(OutputType.FILE);
        String NewFileNamePath = null;
        File directory = new File(".");

        ZonedDateTime date = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fileNameFormat = date.format(formatter);
        String classAndMethodName = fileNameFormat + "_" + scenario.getId();

        try {
            NewFileNamePath = directory.getCanonicalPath() + File.separator + Settings.ScreenPath + File.separator + classAndMethodName + ".png";
            FileUtils.copyFile(scrFile, new File(NewFileNamePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void deleteAllCookies() {
        DriverContext.Driver.manage().deleteAllCookies();
    }

    public static String getNumeroRandom(String numero) {

        String numeroRandom = "";
        Random random = new Random();
        for (int i = 0; i < numero.length(); i++) {
            char caracter = numero.charAt(i);
            if (caracter == '#') {
                int nro = ThreadLocalRandom.current().nextInt(0, 9 + 1);
                numeroRandom = numeroRandom + nro;
            } else {
                numeroRandom = numeroRandom + caracter;
            }

        }
        return numeroRandom;
    }


    public static void cleanAndsend(WebElement element, String value) {
        if (!value.equals("")) {
            element.sendKeys(Keys.HOME, Keys.SHIFT, Keys.END);
            element.sendKeys(value + Keys.TAB);
            CommonUtil.waitPageLoad();
        }
    }
    public static void ScreenshootRuta(String ruta,String nombre) {
        File src = ((TakesScreenshot) DriverContext.Driver).getScreenshotAs(OutputType.FILE);
        File directorio = new File(ruta);
        directorio.mkdir();
        try {
            org.openqa.selenium.io.FileHandler.copy(src, new File(ruta+"\\" + nombre + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String extraerNumero(String cadena) {
        char[] cadena_div = cadena.toCharArray();
        String n = "";
        for (int i = 0; i < cadena_div.length; i++) {
            if (Character.isDigit(cadena_div[i])) {
                n += cadena_div[i];
            }
        }
        return n;
    }
    public static void clickElement(WebElement element) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        // draw a border around the found element

        if (DriverContext.Driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverContext.Driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        WebElement myDynamicElement1 = (new WebDriverWait(DriverContext.Driver, 5))
                .until(ExpectedConditions.elementToBeClickable(element));

        element.click();
//        waitForPageLoaded();
    }

    public static void waitForEnabledElement(WebElement element) {
        new WebDriverWait(DriverContext.Driver, 30).until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void sendText(WebElement element, String value) {

        String selectAll = Keys.chord(Keys.HOME,Keys.SHIFT,Keys.END);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverContext.Driver).withTimeout(20, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        // draw a border around the found element

        if (DriverContext.Driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverContext.Driver).executeScript("arguments[0].style.border='3px solid grey'", element);
        }
        WebElement myDynamicElement1 = (new WebDriverWait(DriverContext.Driver, 5))
                .until(ExpectedConditions.elementToBeClickable(element));

        element.sendKeys(selectAll, value, Keys.TAB);
//        waitForPageLoaded();
    }
}
