package com.LandingPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilityFiles.PropertyFileClass;

public class CrossBrowserTesting {
	
	  WebDriver driver;
         
        @BeforeMethod
        @Parameters("browser")
        public void setUp(String browser) {
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else if ("safari".equalsIgnoreCase(browser)) {
            WebDriverManager.safaridriver().setup();
            SafariOptions options = new SafariOptions();
            options.setCapability("safari:experimentalOptions", "{\"w3c\":false,\"background\":true}");
            driver = new SafariDriver(options);
        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver(options);
        }
        else {
            System.out.println("Unsupported browser: " + browser);
            return;
        }
        }
              
        @Test(priority=0)
        @Parameters("browser")
        public void launchURL(String browser) throws Exception {
        
        String landingPage = PropertyFileClass.getLocator("landingPageURL");
        driver.get(landingPage);
        
        System.out.println("Browser Testing Passed for : "+browser);
        
        HomePageTestCases.testPageTitle(driver);
        System.out.println("-------------------------------------------------------------");
        HomePageTestCases.testHeader(driver);
        
        }
        
        @Test(priority=1)
        @Parameters("browser")
        public void validationTesting() throws Exception
        {	
        	 String landingPage = PropertyFileClass.getLocator("landingPageURL");
             driver.get(landingPage);
             HomePageTestCases.clickContactUs(driver);
             Thread.sleep(5000);
        	  FormSubmissionTest.userNameTesting(driver);
        	  System.out.println("--------------------------------------------------------------");
              FormSubmissionTest.emailIDTesting(driver);
              System.out.println("------------------------------------------------------------------");
              FormSubmissionTest.selectPlotLocation(driver);
              System.out.println("------------------------------------------------------------------");
              FormSubmissionTest.phoneNumberTesting(driver); 
              System.out.println("-------------------------------------------------------------------");
        }
        
        @Test(priority=2)
        @Parameters("browser")
        public void otpRelatedTestCases() throws Exception
        {
        	String landingPage = PropertyFileClass.getLocator("landingPageURL");
            driver.get(landingPage);
            FormSubmissionTest.displayOfOTPSubmitFields(driver);
            Thread.sleep(5000);
            FormSubmissionTest.otpSentSuccessfulMessageTest(driver);
            FormSubmissionTest.checkSubmitAndOTPButtonsEnabledAfterOTPSuccessMessage(driver);
            FormSubmissionTest.checkResendOTPDisplayedAfter2Minutes(driver);
        }
        
        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
    }
}


