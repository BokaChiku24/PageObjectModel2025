package com.pom.pages;

import com.pom.base.Page;
import org.openqa.selenium.By;

public class HomePage extends Page {

    public void goToSupport() {
        driver.findElement(By.cssSelector(".signing>a:nth-child(2)")).click();
    }

    public void goToSignUp() {
        driver.findElement(By.cssSelector(".signup")).click();
    }

    public LoginPage goToLogin() {
        driver.findElement(By.className("zgh-login")).click();
        return new LoginPage();
    }

    public void goToZohoEducation() {

    }

    public void goToLearnMore() {

    }

    public void validateFooterLinks() {

    }

}
