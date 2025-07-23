package com.pom.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Page {

    public static WebDriver driver;
    public static SideMenu side;


    public Page() {
        if(driver==null) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                    + "/src/test/resources/com/pom/SeleniumDrivers/chromedriver");
            Map<String,Object> prefs = new HashMap<String,Object>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            prefs.put("credentials_enable_service", false);
            prefs.put("credential_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            driver = new ChromeDriver(options);
            driver.get("https://www.zoho.com/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            side = new SideMenu(driver);
        }
    }

    // This is a base class for all page classes in the POM framework.
    // It can contain common methods and properties that all pages will inherit.

}
