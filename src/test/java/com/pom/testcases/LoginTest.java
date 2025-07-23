package com.pom.testcases;

import com.pom.pages.HomePage;
import com.pom.pages.LoginPage;
import com.pom.utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String,String> data){
		
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		lp.doLogin(data.get("username"), data.get("password"));
		Assert.fail("Login test failed");
	
		
	}

}
