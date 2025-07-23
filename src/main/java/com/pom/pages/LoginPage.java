package com.pom.pages;

import com.pom.base.Page;
import org.openqa.selenium.By;

public class LoginPage extends Page {

    public void doLogin(String username, String password) {
        // driver.switchTo().frame("zohoiam");
        driver.findElement(By.id("login_id")).sendKeys(username);
        driver.findElement(By.cssSelector("button#nextbtn")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button#nextbtn")).click();

    }

    public void goToSalesMarketing(){

    }

    public void goToFinance() {

    }

}
