package com.pom.accountpages;

import com.pom.base.Page;
import org.openqa.selenium.By;

public class CreateAccountPage extends Page {

    public void createAccount(String accountName){
        driver.findElement(By.id("Crm_Accounts_ACCOUNTNAME_LInput")).sendKeys(accountName);

    }
}
