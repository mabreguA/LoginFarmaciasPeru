package com.iteraprocess.framework.config;

import com.iteraprocess.framework.base.BrowserType;
import com.iteraprocess.framework.base.DeviceType;

import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static void PopulateSettings() throws IOException {
        ConfigReader reader = new ConfigReader();
        reader.ReadProperty();
    }

    private void ReadProperty() throws IOException {

        //Create Property Object
        Properties p = new Properties();

        //Load the Property file available in same package
        String environment = "Environment.properties";

        p.load(getClass().getClassLoader().getResourceAsStream(environment));

        //Get LogPath
        Settings.LogPath = p.getProperty("LogPath");
        //Get DriverType
        Settings.DriverType = p.getProperty("DriverType");
        //GEt ExcelSheetPath
        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");

        //Browser Type
        Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
        //Device Type
        Settings.DeviceType = DeviceType.valueOf(p.getProperty("Device"));
        //PC Mobile Device Width
        Settings.PCMWidth = p.getProperty("PCMobileDeviceWidth");
        //PC Mobile Device Height
        Settings.PCMHeight = p.getProperty("PCMobileDeviceHeight");
        //PC Mobile Device Pixel
        Settings.PCMPixel = p.getProperty("PCMobileDevicePixel");
        //ScreeenShots Path
        Settings.ScreenPath = p.getProperty("ScreenPath");
        //Videos Path
        Settings.VideoPath = p.getProperty("VideoPath");
        //Videos Path
        Settings.RecordVideo = p.getProperty("RecordVideo");
        //Resources Path
        Settings.ResourcePath = p.getProperty("ResourcePath");

        Settings.EmailHost_testing = p.getProperty("EmailHost_testing");
        Settings.EmailEnv = p.getProperty("EmailEnv");
        Settings.Environment = p.getProperty("Environment");
        Settings.DriversPath = p.getProperty("DriversPath");
        Settings.BrowserLess = p.getProperty("BrowserLess");
        Settings.BrowserClose = p.getProperty("BrowserClose");

        //===================== Inkafarma ===========================
          Settings.URLInkafarma = p.getProperty("URLInkafarma");
        //=========================================================

    }
}
