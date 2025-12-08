package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.BasePage;

public class AmazonBusinessPage extends BasePage{

	public AmazonBusinessPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	
}
