package com.pom.testcases;

import com.pom.base.Page;
import org.testng.annotations.AfterTest;

public class BaseTest {
	
	
	@AfterTest
	public void tearDown(){
		
		Page.driver.quit();
		
	}

}
