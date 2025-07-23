package com.pom.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Page {

    public static WebDriver driver;

    public Page() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                + "/src/test/resources/com/pom/SeleniumDrivers/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.zoho.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    // This is a base class for all page classes in the POM framework.
    // It can contain common methods and properties that all pages will inherit.

}
