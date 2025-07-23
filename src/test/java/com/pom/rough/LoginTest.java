package com.pom.rough;

import com.pom.pages.HomePage;
import com.pom.pages.LoginPage;
import com.pom.pages.ZooAppPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        HomePage home  = new HomePage();
        home.goToLogin();
        LoginPage login = new LoginPage();
        login.doLogin("kunalchavan24@gmail.com", "Admin@123$123");
        ZooAppPage zooApp = new ZooAppPage();
        zooApp.goToCliq();
    }
}
