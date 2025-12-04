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

public class MyListener extends BaseTest implements ITestListener, IAnnotationTransformer {

	public void onTestStart(ITestResult result) {
		System.out.println("**** Starting test ***** Test Name=>> " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("**** Test is successful ***** Test Name=>>" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("**** Test failed ! ***** Test Name=>> " + result.getName());

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String dynamicScreenshotName = result.getName() + formatter.format(now);

		File fs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(fs, new File(
					System.getProperty("usr.dir") + "src/test/resources/Screenshot/" + dynamicScreenshotName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("**** Test is skipped  ***** Test Name=>>" + result.getName());
	}

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(MyRetryAnalyzer.class);
	}

}
