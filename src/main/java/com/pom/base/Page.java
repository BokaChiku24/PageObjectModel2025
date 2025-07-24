package com.pom.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pom.extentlisteners.ExtentListeners;
import com.pom.utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static WebDriverWait wait;
    public static ExtentTest test;
    public static String browserName = "chrome";
    public static SideMenu side;


    public Page() {
        if (driver == null) {
            try {
                PropertyConfigurator.configure(System.getProperty("user.dir") +
                        "/src/test/resources/com/pom/properties/log4j.properties");
                fis = new FileInputStream(System.getProperty("user.dir")
                        + "/src/test/resources/com/pom/properties/config.properties");
                config.load(fis);
                fis = new FileInputStream(System.getProperty("user.dir")
                        + "/src/test/resources/com/pom/properties/OR.properties");
                OR.load(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(config.getProperty("browser").equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
                        + config.getProperty("chromedriverpath"));
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("credentials_enable_service", false);
                prefs.put("credential_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-infobars");
                driver = new ChromeDriver(options);
                driver.get(config.getProperty("testsiteurl"));
                driver.manage().window().maximize();
                driver.manage().timeouts().
                        implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
                side = new SideMenu(driver);
            }
        }
    }

    public static void click(String locatorKey) {
        try {
            if (locatorKey.endsWith("_XPATH")) {
                driver.findElement(By.xpath(OR.getProperty(locatorKey))).click();
            } else if (locatorKey.endsWith("_CSS")) {
                driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).click();
            } else if (locatorKey.endsWith("_ID")) {
                driver.findElement(By.id(OR.getProperty(locatorKey))).click();
            }

            log.info("Clicking on an Element : " + locatorKey);
            ExtentListeners.test.log(Status.INFO, "Clicking on an Element : " + locatorKey);
        } catch (Throwable t) {

            log.error("Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
            ExtentListeners.test.log(Status.FAIL,
                    "Error while Clicking on an Element : " + locatorKey + " error message : " + t.getMessage());
            //Assert.fail(t.getMessage());

        }

    }

    public static boolean isElementPresent(String locatorKey) {

        try {
            if (locatorKey.endsWith("_XPATH")) {
                driver.findElement(By.xpath(OR.getProperty(locatorKey)));
            } else if (locatorKey.endsWith("_CSS")) {
                driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
            } else if (locatorKey.endsWith("_ID")) {
                driver.findElement(By.id(OR.getProperty(locatorKey)));
            }
        } catch (Throwable t) {

            log.info("Element not found : " + locatorKey);
            ExtentListeners.test.log(Status.INFO, "Element not found : " + locatorKey);
            return false;

        }

        log.info("Finding an Element : " + locatorKey);
        ExtentListeners.test.log(Status.INFO, "Finding an Element : " + locatorKey);
        return true;
    }

    public static void type(String locatorKey, String value) {
        try {
            if (locatorKey.endsWith("_XPATH")) {
                driver.findElement(By.xpath(OR.getProperty(locatorKey))).sendKeys(value);
            } else if (locatorKey.endsWith("_CSS")) {
                driver.findElement(By.cssSelector(OR.getProperty(locatorKey))).sendKeys(value);
            } else if (locatorKey.endsWith("_ID")) {
                driver.findElement(By.id(OR.getProperty(locatorKey))).sendKeys(value);
            }
            log.info("typing in an Element : " + locatorKey + " entered the value as : " + value);
            ExtentListeners.test.log(Status.INFO,
                    "typing in an Element : " + locatorKey + " entered the value as : " + value);
        } catch (Throwable t) {

            log.error("Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
            ExtentListeners.test.log(Status.FAIL,
                    "Error while typing in an Element : " + locatorKey + " error message : " + t.getMessage());
            Assert.fail(t.getMessage());

        }

    }

    public static void select(String locatorKey, String value) {
        WebElement dropdown = null;
        try {

            if (locatorKey.endsWith("_XPATH")) {
                dropdown = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
            } else if (locatorKey.endsWith("_CSS")) {
                dropdown = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
            } else if (locatorKey.endsWith("_ID")) {
                dropdown = driver.findElement(By.id(OR.getProperty(locatorKey)));
            }

            Select select = new Select(dropdown);
            select.selectByVisibleText(value);
            log.info("Selecting an Element : " + locatorKey + " selected the value as : " + value);
            ExtentListeners.test.log(Status.INFO,
                    "Selecting an Element : " + locatorKey + " selected the value as : " + value);
        } catch (Throwable t) {

            log.error("Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
            ExtentListeners.test.log(Status.FAIL,
                    "Error while selecting an Element : " + locatorKey + " error message : " + t.getMessage());
            Assert.fail(t.getMessage());

        }

    }

    /* Initialization */
    public void setUp() {
        // loading the log file
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/com/pom/properties/log4j.properties");

        // loading the OR and Config properties
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/com/pom/properties/config.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            config.load(fis);
            log.info("Config properties loaded !!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            OR.load(fis);
            log.info("OR properties loaded !!!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (config.getProperty("browser").equals("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            log.info("Launching Chrome !!!");

        } else if (config.getProperty("browser").equals("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            log.info("Launching Firefox !!!");

        }

        driver.get(config.getProperty("testsiteurl"));
        log.info("Navigated to : " + config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
        wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));

		/*
		try {
			DbManager.setMysqlDbConnection();
			log.info("DB Connection established !!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }

}
