package com.amazon.customlisteners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer{

	int retryCount=0;
	int maxRetryCount=2;
			
	@Override
	public boolean retry(ITestResult result) {
		if(retryCount<maxRetryCount &&!result.isSuccess()) {
			retryCount++;
			System.out.println("Retry test :"+result.getName()+"=> Attempts count => "+retryCount);
			return true;
		}
		return false;
	}

}
