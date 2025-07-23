package com.pom.rough;

import com.pom.pages.crmpages.accountpages.AccountsPage;
import com.pom.pages.crmpages.accountpages.CreateAccountPage;
import com.pom.base.Page;
import com.pom.pages.crmpages.CRMHomePage;
import com.pom.pages.HomePage;
import com.pom.pages.LoginPage;
import com.pom.pages.ZohoAppPage;

public class LoginTest {
    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        LoginPage login = homePage.goToLogin();
        ZohoAppPage zooApp = login.doLogin("kunalchavan24@gmail.com", "Admin@123$123");
        CRMHomePage crm = zooApp.goToCRM();
        AccountsPage account =  Page.side.goToAccounts();
        CreateAccountPage createAccountPage = account.goToCreateAccount();
        createAccountPage.createAccount("TestUser");

    }
}
