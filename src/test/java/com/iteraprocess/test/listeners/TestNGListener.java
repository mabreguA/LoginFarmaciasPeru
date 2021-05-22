package com.iteraprocess.test.listeners;

import com.iteraprocess.framework.config.Settings;
import com.iteraprocess.framework.utilities.CommonUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Listener onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Listener onTestSuccess");
    }

    @Override
    public void onTestFailure(ITestResult tr) {

        System.out.println("Listener onTestFailure");

        Settings.Logs.Write("Executed time " + CommonUtil.getExecutiveTime(tr.getEndMillis(), tr.getStartMillis()));
        Settings.Logs.Write("Error found " + tr.getThrowable().getMessage());

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Listener onTestSkipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Listener onTestFailedButWithinSuccessPercentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Listener onStart");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Listener onFinish");
    }
}

