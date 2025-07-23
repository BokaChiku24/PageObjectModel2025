package com.pom.rough;

import com.pom.accountpages.AccountPage;
import com.pom.accountpages.CreateAccountPage;
import com.pom.base.Page;
import com.pom.crmpages.CRMHomePage;
import com.pom.pages.HomePage;
import com.pom.pages.LoginPage;
import com.pom.pages.ZooAppPage;

public class LoginTest {
    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        LoginPage login = homePage.goToLogin();
        ZooAppPage zooApp = login.doLogin("kunalchavan24@gmail.com", "Admin@123$123");
        CRMHomePage crm = zooApp.goToCRM();
        AccountPage account =  Page.side.goToAccounts();
        CreateAccountPage createAccountPage = account.goToCreateAccount();
        createAccountPage.createAccount("TestUser");

    }
}
