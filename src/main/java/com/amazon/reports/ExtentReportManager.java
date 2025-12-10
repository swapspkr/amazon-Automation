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
		extent = new ExtentReports();
		extent.attachReporter(sparkreporter);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Amazon Automation Report");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
	}

	public static void createTest(String testName, String groups, String className, String xmlTestName, String author) {
		ExtentTest extentTest = extent.createTest("<b>" + testName + "</b>")
				.assignCategory(groups, className, xmlTestName)
				.assignAuthor(author)
				.assignDevice("Chrome");
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

	public static void createCustomTable(
	        String totalTests, 
	        String passedTests, 
	        String failedTests, 
	        String skippedTests, 
	        String passPercentage,
	        String browser,
	        String url) {

	    String customTable =
	            "<table style='width:100%; border: 2px solid Brown; border-collapse: collapse;'>"
	            + "<tr style='background-color: #ff555e;'>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 15%;'>Total Tests</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 12%;'>Passed</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 12%;'>Failed</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 12%;'>Skipped</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 12%;'>Passed%</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 12%;'>Browser</th>"
	            + "<th style='border: 1px solid Brown; text-align: center; font-size: 10px; width: 21%;'>URL</th>"
	            + "</tr>"

	            + "<tr>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; font-weight: bold; width: 15%;'>" 
	                    + totalTests + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: green; font-weight: bold; width: 12%;'>" 
	                    + passedTests + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: red; font-weight: bold; width: 12%;'>" 
	                    + failedTests + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: orange; font-weight: bold; width: 12%;'>" 
	                    + skippedTests + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: blue; font-weight: bold; width: 12%;'>" 
	                    + passPercentage + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: blue; font-weight: bold; width: 15%;'>" 
	                    + browser + "</td>"
	            + "<td style='border: 1px solid black; text-align: center; font-size: 10px; color: blue; font-weight: bold; width: 21%;'>" 
	                    + url + "</td>"
	            + "</tr>"

	            + "</table>";

	    String testSummaryKey =
	            "<div style='text-align: center; font-weight: bold; font-size: 14px;'>Test<br>Summary</div>";

	    extent.setSystemInfo(testSummaryKey, customTable);
	}


}
