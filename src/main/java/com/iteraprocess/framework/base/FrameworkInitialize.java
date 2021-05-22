package com.iteraprocess.framework.base;

import com.iteraprocess.framework.config.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class FrameworkInitialize extends Base{

    public static void InitializeBrowser(BrowserType browserType, DeviceType deviceType) {

        WebDriver driver = null;
        DesiredCapabilities capabilities = null;
        Map<String, Object> chromeOptions = new HashMap<>();
        String pathDriver = Settings.DriversPath;
        String extension = "";

        String osFound = System.getProperty("os.name").toLowerCase();
        if (osFound.contains("windows")) {
            pathDriver += "win";
            extension = ".exe";
        } else if (osFound.contains("linux")) {
            pathDriver += "linux";
        } else {
            pathDriver += "mac";
        }

        Settings.Logs.Write("Configuration properties :: Browser [" + Settings.BrowserType + "] - Device [" + Settings.DeviceType + "] - Operative System [" + osFound + "]");

        switch (deviceType) {
            case PC: {
                if (browserType.toString().toUpperCase().equals("CHROME")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--start-maximized");
                    options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
                    options.addArguments("--disable-web-security");
                    options.addArguments("--allow-running-insecure-content");
                    options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
                    options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
                    options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
                    options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
                    options.addArguments("--disable-gpu");
                    options.addArguments("--verbose");
//                    options.addArguments("--whitelisted-ips='172.30.19.176'");
//                    options.addArguments("--proxy-server=proxybc.pps.com.pe:8080");
                    if(Settings.BrowserLess.equals("true")){
                        options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
                    }
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                }else if (browserType.toString().toUpperCase().equals("IE")) {
                    InternetExplorerOptions options = new InternetExplorerOptions();
                    capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability("ignoreZoomSetting", options);

                }else if (browserType.toString().toUpperCase().equals("FIREFOX")) {
                    FirefoxOptions options = new FirefoxOptions();
                    if(Settings.BrowserLess.equals("true")){
                        options.setHeadless(true);
                    }
//                    capabilities = DesiredCapabilities.firefox();
//                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
                }

                break;
            }
            case PCMobile: {

                Map<String, Object> deviceMetrics = new HashMap<String, Object>();
                deviceMetrics.put("width", Settings.PCMWidth);
                deviceMetrics.put("height", Settings.PCMHeight);
                deviceMetrics.put("pixelRatio", Settings.PCMPixel);
                Map<String, Map<String, Object>> mobileEmulation = new HashMap<>();

                mobileEmulation.put("deviceMetrics", deviceMetrics);
//                mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

//                Map<String, Object> chromeOptions = new Map<String, Object>();
//                chromeOptions.put("mobileEmulation", mobileEmulation);

                // Apple iPhone 6, Apple iPhone 6 Plus, iPhone 7, iPhones 7 Plus.
                // Galaxy S7 Edge, Galaxy J3, Galaxy J5, Galaxy S7 y Galaxy J7.
//                String mobileNAme = "Apple iPhone 6"; //Google Nexus 5
//                Map<String, String> mobileEmulation = new HashMap<>();
//                mobileEmulation.put("deviceName", mobileNAme);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
//                options.addArguments("--use-fake-device-for-media-stream=1");
                options.addArguments("--use-fake-ui-for-media-stream=1");
                options.addArguments("--window-size=" + Settings.PCMWidth + "," + Settings.PCMHeight);

                chromeOptions.put("mobileEmulation", mobileEmulation);
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                break;
            }
            case Mobile: {
                break;
            }
            default: {

            }
        }

        switch (browserType) {
            case Chrome: {
                System.setProperty("webdriver.chrome.driver", pathDriver + "/chromedriver" + extension);
                driver = new ChromeDriver(capabilities);
//                driver = new RemoteWebDriver(capabilities);
                break;
            }
            case Firefox: {
                System.setProperty("webdriver.gecko.driver", pathDriver + "/geckodriver" + extension);
                driver = new FirefoxDriver();
                break;
            }
            case IE: {
                System.setProperty("webdriver.ie.driver", pathDriver + "/IEDriverServer" + extension);
                driver = new InternetExplorerDriver();
                break;
            }
        }

        //Set the Driver
        DriverContext.setDriver(driver);
        //Browser
        DriverContext.Browser = new Browser(driver);
    }
}
