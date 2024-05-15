package com.LandingPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilityFiles.PropertyFileClass;

public class HomePageTestCases {

    public static void testPageTitle(WebDriver driver) {
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = PropertyFileClass.getLocator("pageTitle");
        if (expectedPageTitle.equalsIgnoreCase(actualPageTitle)) {
            System.out.println("Page title is correct");
        } else {
            System.out.println("Page title is incorrect");
        }
    }
    public static void testHeader(WebDriver driver)
    {
    	String expectedH3HeaderText = "Bengaluru’s No.1 home construction company!";
    	System.out.println(expectedH3HeaderText);
    	
    	String browserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toLowerCase();
    	
    	if(browserName.contains("chrome")) {
    	String chrome_Header1Text = driver.findElement(By.xpath(PropertyFileClass.getLocator("h3Header1"))).getText();
    	String chrome_Header2Text = driver.findElement(By.xpath(PropertyFileClass.getLocator("h3Header2"))).getText();
    	String chrome_Header3Text = driver.findElement(By.xpath(PropertyFileClass.getLocator("h3Header3"))).getText();
    	String chromeHeaderText = chrome_Header1Text + " "+chrome_Header2Text +" " + chrome_Header3Text;
    	if (chromeHeaderText.equalsIgnoreCase(expectedH3HeaderText)) {
            System.out.println("Chrome - H3 Header of Webpage title is correct" + ": "+chromeHeaderText);
        } else {
            System.out.println("Chrome - H3 Header of Webpage title is incorrect" + ": "+chromeHeaderText);
        }
    	}
    	else if(browserName.contains("edge")) {
    	String edgeHeaderText = driver.findElement(By.xpath(PropertyFileClass.getLocator("h1HeaderEdge"))).getText();
    	if (edgeHeaderText.equalsIgnoreCase(expectedH3HeaderText)) {
            System.out.println("Edge - Header of Webpage title is correct" + ": "+edgeHeaderText);
        } else {
            System.out.println("Edge - H3 Header of Webpage title is incorrect" + ": "+edgeHeaderText);
        }
    	}
    }
    public static void clickContactUs(WebDriver driver)
    {
    	WebElement buttonContactUs = driver.findElement(By.className(PropertyFileClass.getLocator("contactUs")));
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(buttonContactUs));
        buttonContactUs.click();
    }
    
    

}
