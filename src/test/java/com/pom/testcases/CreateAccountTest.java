package com.pom.testcases;

import com.pom.base.Page;
import com.pom.pages.HomePage;
import com.pom.pages.LoginPage;
import com.pom.pages.ZohoAppPage;
import com.pom.pages.crmpages.accountpages.AccountsPage;
import com.pom.pages.crmpages.accountpages.CreateAccountPage;
import com.pom.utilities.Utilities;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CreateAccountTest {


	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String,String> data){
		HomePage homePage = new HomePage();
		LoginPage login = homePage.goToLogin();
		ZohoAppPage zooApp = login.doLogin("kunalchavan24@gmail.com", "Admin@123$123");
		zooApp.goToCRM();
		AccountsPage account = Page.side.goToAccounts();
		CreateAccountPage cap = account.goToCreateAccount();
		cap.createAccount(data.get("accountname"));
		//Assert.fail("Create account test failed");
		
	}

}
