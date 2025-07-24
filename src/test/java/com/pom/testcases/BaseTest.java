package com.pom.testcases;

import com.pom.base.Page;
import org.testng.annotations.AfterSuite;

public class BaseTest {
	
	
	@AfterSuite
	public void tearDown(){
		
		Page.driver.quit();
		
	}

}
