package com.pom.pages;

import com.pom.base.Page;
import com.pom.crmpages.CRMHomePage;
import org.openqa.selenium.By;

public class ZooAppPage extends Page {

    public void goToCliq(){
        driver.findElement(By.cssSelector("div.zl-nth-child1")).click();
    }

    public void goToPeople() {
        driver.findElement(By.cssSelector("div.zl-nth-child2")).click();
    }

    public CRMHomePage goToCRM(){
        driver.findElement(By.cssSelector("div.zl-nth-child3")).click();
        return new CRMHomePage();
    }
}
