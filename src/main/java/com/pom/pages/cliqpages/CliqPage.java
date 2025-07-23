package com.pom.pages.cliqpages;

import com.pom.base.Page;
import org.openqa.selenium.By;

public class CliqPage extends Page {

    public void verifyJoinExistingBusiness() {
        driver.findElement(By.cssSelector("div#joinorcreate div.flexC  div.fdirC:nth-child(1) div.btn")).isDisplayed();
    }

}
