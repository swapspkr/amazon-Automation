package com.amazon.utils;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CustomWait {

	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public CustomWait(WebDriver driver,Duration timeout) {
		this.driver=driver;
		wait= new WebDriverWait(driver,timeout);
	}
	
	
	public void waitForVisibilityOfElement(WebElement ele) {
		try{
			wait.until(ExpectedConditions.visibilityOf(ele));
		}catch(TimeoutException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void waitForElementToBeClickable(WebElement ele) {
		
		try{
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}catch(TimeoutException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void waitForElementToBeVisible(WebElement ele) {
		
		try{
			wait.until(ExpectedConditions.invisibilityOf(ele));
		}catch(TimeoutException e) {
			System.err.println(e.getMessage());
		}
	}
}
