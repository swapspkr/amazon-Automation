package com.amazon.customlisteners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.amazon.basetest.BaseTest;
import com.amazon.reports.ExtentReportManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class MyListener extends BaseTest implements ITestListener, IAnnotationTransformer {

	@Override
	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getName()
				.substring(result.getTestClass().getName().lastIndexOf(".") + 1);
		String methodName = result.getMethod().getMethodName();
		logger.info("Started Test: " + className + "=>" + methodName);

		String xmlTest = result.getTestContext().getName();
		String groupList[] = result.getTestContext().getIncludedGroups();
		String includedGroupList = String.join(",", groupList);
		
		
		ExtentReportManager.createTest(className+"."+methodName, includedGroupList,className,xmlTest, "John");
		ExtentReportManager.getTest().log(Status.INFO, "Test Started" + className + "=>" + methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String className = result.getTestClass().getName()
				.substring(result.getTestClass().getName().lastIndexOf(".") + 1);
		String methodName = result.getMethod().getMethodName();

		ExtentReportManager.getTest().log(Status.PASS, "Test Successful" + className + "=>" + methodName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String className = result.getTestClass().getName()
				.substring(result.getTestClass().getName().lastIndexOf(".") + 1);
		String methodName = result.getMethod().getMethodName();
		logger.info("Test Failed : " + className + "=>" + methodName);

		ExtentReportManager.getTest().log(Status.FAIL, "Test Failed" + className + "=>" + methodName);
		ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String dynamicScreenshotName = result.getName() +"_"+ formatter.format(now)+".png";

		File fs = ((TakesScreenshot) getLocalDriver()).getScreenshotAs(OutputType.FILE);
		try {
			String screenShotPath = System.getProperty("user.dir") + "/src/test/resources/Screenshot/"
					+ dynamicScreenshotName;
			File destFile = new File(screenShotPath);
			FileUtils.copyFile(fs, destFile);
			ExtentReportManager.getTest().fail("Screenshot for failure",
					MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		String className = result.getTestClass().getName()
				.substring(result.getTestClass().getName().lastIndexOf(".") + 1);
		String methodName = result.getMethod().getMethodName();

		ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped" + className + "=>" + methodName);
		ExtentReportManager.getTest().log(Status.SKIP, result.getThrowable());
		
		logger.info("Test Skipped : " + className + "=>" + methodName);
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(MyRetryAnalyzer.class);
	}

}
