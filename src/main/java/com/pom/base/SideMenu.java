package com.pom.base;

import com.pom.accountpages.AccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenu {

    WebDriver driver;

    public SideMenu(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHome(){

    }

    public void goToLeads(){

    }

    public void goToContacts(){

    }

    public AccountPage goToAccounts(){
        driver.findElement(By.id("lm_module_Accounts")).click();
        return new AccountPage();
    }

    public void goToDeals(){

    }

    public void goToMeetings(){

    }

    public void signOut() {
        // Implement sign out logic here
    }

}
