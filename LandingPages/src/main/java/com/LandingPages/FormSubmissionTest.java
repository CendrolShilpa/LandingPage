package com.LandingPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilityFiles.ExcelReader;
import utilityFiles.PropertyFileClass;

public class FormSubmissionTest {
	
	public static void userNameInputTest(WebDriver driver, String userName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PropertyFileClass.getLocator("name.input"))));
		// WebElement usernameInput = driver.findElement(By.id(PropertyFileClass.getLocator("name.input")));
        usernameInput.sendKeys(userName);
	}
	
	public static void emailIDInputTest(WebDriver driver, String emailID)
	{
		WebElement emailInput = driver.findElement(By.id(PropertyFileClass.getLocator("email.input")));
        emailInput.sendKeys(emailID);
	}
	
	public static void selectPlotLocationTest(WebDriver driver)
	{
		WebElement selectDropdownCountry = driver.findElement(By.id(PropertyFileClass.getLocator("selectCity.dropdown")));
		 Select selectCountry = new Select(selectDropdownCountry);
		 selectCountry.selectByVisibleText(PropertyFileClass.getLocator("cityValue"));
	}
	
	public static void inputPhoneNumberTest(WebDriver driver, String phoneNumber)
	{
		WebElement phoneNumberInput = driver.findElement(By.id(PropertyFileClass.getLocator("phone.input")));
        phoneNumberInput.sendKeys(phoneNumber);
	}
	
	 public static void userNameTesting(WebDriver driver) throws Exception {
		 
		 Object[][] validUsernames = ExcelReader.readData("Usernames", "ValidUsernames");

	        for (Object[] data : validUsernames) {
	            String validUsername = (String) data[0];

	            WebElement usernameInput = driver.findElement(By.id(PropertyFileClass.getLocator("name.input")));
	            usernameInput.sendKeys(validUsername);
	            
	            clickGetOTP(driver);

	            if (!driver.getPageSource().contains(PropertyFileClass.getLocator("userName.min3Char"))||!driver.getPageSource().contains(PropertyFileClass.getLocator("userName.invalidName"))) {
	                System.out.println("Validation passed for valid username: " + validUsername);
	            }
	            usernameInput.clear();
	        }
	        
	        Object[][] invalidUsernames = ExcelReader.readData("Usernames", "InvalidUsernames");

		        for (Object[] data : invalidUsernames) {
		            String inValidUsername = (String) data[0];

		            WebElement usernameInput = driver.findElement(By.id(PropertyFileClass.getLocator("name.input")));
		            usernameInput.sendKeys(inValidUsername);
		            String userInputValue = usernameInput.getAttribute("value");
		            // System.out.println(userInputValue);
		            
		            clickGetOTP(driver);
		            
		            String errorMessage = driver.findElement(By.className(PropertyFileClass.getLocator("invalidUsername.errorMessage"))).getText();
		           
		            if(userInputValue.isEmpty()&&driver.getPageSource().contains(PropertyFileClass.getLocator("userName.mandatory"))) {
		            	System.out.println("Validation passed for empty username: " + userInputValue + " : " + errorMessage);
		            }
		            else if (driver.getPageSource().contains(PropertyFileClass.getLocator("userName.min3Char"))||driver.getPageSource().contains(PropertyFileClass.getLocator("userName.invalidName"))) {
		                System.out.println("Validation passed for invalid username: " + inValidUsername + " : " + errorMessage);
		            }
		            else
		            {
		            	System.out.println("Validation falied for invalid username: " + inValidUsername);
		            }
		            usernameInput.clear();
		        }
	    }
	 
	 public static void emailIDTesting(WebDriver driver) throws Exception {
		 
		 Object[][] validEmailIDs = ExcelReader.readData("EmailIDs", "ValidEmailID");

	        for (Object[] data : validEmailIDs) {
	            String validEmailID = (String) data[0];

	            WebElement emailInput = driver.findElement(By.id(PropertyFileClass.getLocator("email.input")));
	            emailInput.sendKeys(validEmailID);
	            
	            clickGetOTP(driver);

	            if (!driver.getPageSource().contains(PropertyFileClass.getLocator("email.invalidEmail"))) {
	                System.out.println("Validation passed for valid EmailID: " + validEmailID);
	            }
	            emailInput.clear();
	        }
	        
	        Object[][] invalidEmailIDs = ExcelReader.readData("EmailIDs", "InValidEmailID");

		        for (Object[] data : invalidEmailIDs) {
		            String inValidEmailID = (String) data[0];

		            WebElement emailInput = driver.findElement(By.id(PropertyFileClass.getLocator("email.input")));
		            emailInput.sendKeys(inValidEmailID);
		            String userEmailValue = emailInput.getAttribute("value");
		            
		            clickGetOTP(driver);

		            if (driver.getPageSource().contains(PropertyFileClass.getLocator("email.invalidEmail"))) {
		                System.out.println("Validation passed for invalid EmailID: " + inValidEmailID);
		            }
		            if(userEmailValue.isEmpty()&&driver.getPageSource().contains(PropertyFileClass.getLocator("email.mandatory"))) {
		            	System.out.println("Validation passed for empty EmailID: " + userEmailValue);
		            }
		            emailInput.clear();
		        }
	    }
	 
	 public static void selectPlotLocation(WebDriver driver)
	 {
		 selectPlotLocationTest(driver);
		 clickGetOTP(driver);
		 WebElement cityBangalore = driver.findElement(By.xpath(PropertyFileClass.getLocator("bangaloreCity.list")));
		 if(cityBangalore.isSelected())
			 {
			 	System.out.println("Bangalore City Selected : " + cityBangalore.isSelected());
			 }
		 else if(driver.getPageSource().contains(PropertyFileClass.getLocator("location.mandatory")))
		 	{
			 	System.out.println("City field is Mandatory - Error Message Displayed");
		 	}
	 }
	 
	 	public static void phoneNumberTesting(WebDriver driver) throws Exception {
		 
	 		Object[][] validPhoneNumbers = ExcelReader.readData("PhoneNumbers", "ValidPhoneNumber");

	        for (Object[] data : validPhoneNumbers) {
	            String validPhoneNumber = (String) data[0];

	            WebElement phoneNumberInput = driver.findElement(By.id(PropertyFileClass.getLocator("phone.input")));
	            phoneNumberInput.sendKeys(validPhoneNumber);
	            
	            clickGetOTP(driver);

	            if (!driver.getPageSource().contains(PropertyFileClass.getLocator("phone.inValidPhone"))) {
	                System.out.println("Validation passed for valid Phone Number: " + validPhoneNumber);
	            }
	            phoneNumberInput.clear();
	        }
	        
	        Object[][] invalidPhoneNumbers = ExcelReader.readData("PhoneNumbers", "InValidPhoneNumber");

		        for (Object[] data : invalidPhoneNumbers) {
		            String inValidPhoneNumber = (String) data[0];

		            WebElement phoneInput = driver.findElement(By.id(PropertyFileClass.getLocator("phone.input")));
		            phoneInput.sendKeys(inValidPhoneNumber);
		            String userPhoneNumberValue = phoneInput.getAttribute("value");
		            
		            clickGetOTP(driver);

		            if (driver.getPageSource().contains(PropertyFileClass.getLocator("phone.inValidPhone"))) {
		            	String errorMessage = driver.findElement(By.xpath(PropertyFileClass.getLocator("inValidPhone.errorMessage"))).getText();
		                System.out.println("Validation passed for invalid Phone Number : " + inValidPhoneNumber + " : "+errorMessage);
		            }
		            if(userPhoneNumberValue.isEmpty()&&driver.getPageSource().contains(PropertyFileClass.getLocator("phone.mandatory"))) {
		            	System.out.println("Validation passed for empty Phone Number: " + userPhoneNumberValue);
		            }
		            phoneInput.clear();
		        }
	    }
	 
	 public static void clickGetOTP(WebDriver driver)
	 {
		 driver.findElement(By.xpath(PropertyFileClass.getLocator("get.OTP.button"))).click();
	 }
	 
	 public static void formFilling(WebDriver driver)
	 {
		 HomePageTestCases.clickContactUs(driver);
		 userNameInputTest(driver,"Shilpa");
		 emailIDInputTest(driver,"shilpa@cendrol.com");
		 selectPlotLocationTest(driver);
		 inputPhoneNumberTest(driver,"9986589632");
		 clickGetOTP(driver);	 	 
	 }
	 
	 public static void otpSentSuccessfulMessageTest(WebDriver driver)
	 {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(PropertyFileClass.getLocator("otpSent.messageLocator"))));
		 if(driver.getPageSource().contains(PropertyFileClass.getLocator("otpSent.message")))
		 {
			 System.out.println("OTP Sent Success message displayed");
		 }
		 else
		 {
			 System.out.println("Error : OTP Sent Success message not displayed");
		 }
	 }

	 public static void displayOfOTPSubmitFields(WebDriver driver)
	 {
		 formFilling(driver);
			    if (driver.findElement(By.xpath(PropertyFileClass.getLocator("submitButton"))).isDisplayed() &&
			        driver.findElement(By.className(PropertyFileClass.getLocator("enterOTPText"))).isDisplayed() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input1"))).isDisplayed() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input2"))).isDisplayed() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input3"))).isDisplayed() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input4"))).isDisplayed()) {

			        System.out.println("OTP related all the fields displayed after clicking on Get OTP button");
			    } else {
			        System.out.println("Error : OTP related all the fields are not displayed on click of Get OTP button");
			    }
		 }
	 
	 public static void checkSubmitAndOTPButtonsEnabledAfterOTPSuccessMessage(WebDriver driver)
	 {
		 otpSentSuccessfulMessageTest(driver);
		 if (driver.findElement(By.xpath(PropertyFileClass.getLocator("timer"))).isDisplayed() &&
				 	driver.findElement(By.xpath(PropertyFileClass.getLocator("submitButton"))).isEnabled() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input1"))).isEnabled() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input2"))).isEnabled() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input3"))).isEnabled() &&
			        driver.findElement(By.xpath(PropertyFileClass.getLocator("otp.input4"))).isEnabled()) {

			        System.out.println("OTP Fields and Submit Button got Enabled after OTP Sent success message");
			    } else {
			        System.out.println("Error : OTP Fields and Submit Button not Enabled after OTP Sent success message");
			    }
	 }
	 
	
	 public static void checkResendOTPDisplayedAfter2Minutes(WebDriver driver)
	 {
		 	By timerLocator = By.xpath(PropertyFileClass.getLocator("timer"));

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
	        wait.until(ExpectedConditions.textToBe(timerLocator, "Time remaining   0:00"));

	        WebElement resendOTPButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PropertyFileClass.getLocator("resendOTPButton"))));

	        if (resendOTPButton.isDisplayed()) {
	            System.out.println("Resend OTP button is displayed after 2 minutes.");
	        } else {
	            System.out.println("Error : Resend OTP button is not displayed after 2 minutes.");
	        }
	 }
	 
	 
	

}
