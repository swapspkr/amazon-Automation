package com.amazon.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.basetest.BaseTest;

public class RegistrationPageTest extends BaseTest{
	
	
	@BeforeMethod
	public void setupPage() {
	
		// open registration page from homepage
		homePage.openRegistrationPage();
		
	}

	@Test
	public void isLogoPresentTest() {
		
		Assert.assertTrue(basepage.verifyLogo(),"ERROR -- Logo is not Present");
	}
}
