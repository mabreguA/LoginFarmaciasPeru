package com.iteraprocess.test.steps;

import com.iteraprocess.framework.base.DriverContext;
import com.iteraprocess.framework.base.FrameworkInitialize;
import com.iteraprocess.framework.config.ConfigReader;
import com.iteraprocess.framework.config.Settings;
import com.iteraprocess.framework.utilities.CommonUtil;
import com.iteraprocess.framework.utilities.LogUtil;
import com.iteraprocess.framework.utilities.ScreenRecordUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class TestInitialize extends FrameworkInitialize {

    @Before
    public void Initialize(Scenario scenario) throws Exception {

        //Initialize Config
        ConfigReader.PopulateSettings();

        //Logging
        Settings.Logs = new LogUtil();
        Settings.Logs.CreateLogFile();
        Settings.Logs.Write("Framework Initialize");

        InitializeBrowser(Settings.BrowserType, Settings.DeviceType);

        Capabilities cap = ((RemoteWebDriver) DriverContext.Driver).getCapabilities();
        Settings.Logs.Write("Device - Browser initialized :: Platform [" + cap.getPlatform() + "] - Browser [" + cap.getBrowserName() + "] - Version [" + cap.getVersion() + "]");
        DriverContext.Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        if(Settings.RecordVideo.equals("true")){
            ScreenRecordUtil.startRecording();
        }
    }

    @After
    public void TearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            if(!CommonUtil.getFeatureName(scenario.getId()).contains("services")){
                CommonUtil.takeScreenshot(DriverContext.Driver, scenario);
            }

//            ReportingUtil.WriteTestResults(Settings.ReportingConnection, Settings.FeatureContext, Settings.ScenarioContext,"I should see the username with hello", "", "FAILED");
        } else {

//            ReportingUtil.WriteTestResults(Settings.ReportingConnection, Settings.FeatureContext, Settings.ScenarioContext, "I should see the username with hello", "", "PASSED");
        }

        System.out.println("#####################################################################");
        System.out.println("Scenario ["+scenario.getName()+"] - Status ["+scenario.getStatus()+"]");
        System.out.println("#####################################################################");

        if(Settings.RecordVideo.equals("true")){
            ScreenRecordUtil.stopRecording(scenario.getName());
        }

//        CommonUtil.deleteAllCookies();
//        if(Settings.BrowserClose.equals("true")){
//            DriverContext.Driver.quit();
//        }
    }
}