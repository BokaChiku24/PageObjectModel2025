package com.pom.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.pom.utilities.ExcelReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Page {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")
            + "/src/test/resources/com/pom/excel/testdata.xlsx");
    public static ExtentReports report;
    public static WebDriverWait wait;
    public static ExtentTest test;
    public static String browserName = "chrome";
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
