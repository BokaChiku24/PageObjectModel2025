package com.pom.pages;

import com.pom.base.Page;
import org.openqa.selenium.By;

public class ZooAppPage extends Page {

    public void goToCliq(){
        driver.findElement(By.cssSelector("div.zl-nth-child1")).click();
    }

    public void goToPeople() {
        driver.findElement(By.cssSelector("div.zl-nth-child2")).click();
    }
}
