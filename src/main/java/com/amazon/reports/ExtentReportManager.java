package com.amazon.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	
	public static void setupExtentReport() {
		
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("./extent-report/ExtentReport.html");
		extent= new ExtentReports();
		extent.attachReporter(sparkreporter);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Amazon Automation Report");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
	}
	
	public static void createTest(String testName,String groups,String className,String xmlTestName,String author) {
		ExtentTest extentTest = extent.createTest("<b>"+testName+"</b>")
						.assignCategory(groups,className,xmlTestName).assignAuthor(author).assignDevice("Chrome");
		test.set(extentTest);
	}
	
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static ExtentReports getReport() {
		return extent;
	}
	
	public static void flushReport() {
		extent.flush();
	}
}
