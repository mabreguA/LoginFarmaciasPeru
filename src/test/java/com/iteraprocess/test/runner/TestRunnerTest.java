package com.iteraprocess.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Listeners;

@Listeners(com.iteraprocess.test.listeners.TestNGListener.class)
@CucumberOptions(
        features = {"src/test/java/com/iteraprocess/test/features/"},
//        features = {"src\\test\\java\\com\\iteraprocess\\test\\features"},
        glue = {"com/iteraprocess/test/steps"},
//        plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"},
        plugin = {"pretty","json:target/cucumber.json"},
        tags = {"@generarData"})
public class TestRunnerTest extends AbstractTestNGCucumberTests {


}
