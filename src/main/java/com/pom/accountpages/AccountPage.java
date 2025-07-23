package com.pom.accountpages;

import com.pom.base.Page;
import org.openqa.selenium.By;

import java.time.Duration;

public class AccountPage extends Page {

    public CreateAccountPage goToCreateAccount(){
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath(".//button[@aria-label='Create Account']")).click();
        return new CreateAccountPage();
    }

    public void goToImportAccount(){

    }

}
