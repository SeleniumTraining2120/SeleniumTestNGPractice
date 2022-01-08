package com.testNGPract;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenCapture {

	WebDriver driver;
	Properties prop;

	@BeforeTest
	public void loadLocators() throws IOException  {
		FileInputStream fis = new FileInputStream("D:\\CEP_WorkSpace\\TestNGProject\\Properties\\Locators.properties");
		prop = new Properties();
		prop.load(fis);
	}

	@BeforeClass
	public void invokeBrowserInstance() {
		System.setProperty("webdriver.chrome.driver", "D:\\CEP_WorkSpace\\TestNGProject\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demoqa.com");

	}
	
	 

	public boolean fluentWait(By by) {

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
			Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebElement element = driver.findElement(by);
					return element.isDisplayed();
				}
			};
			wait.until(function);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {

			return false;
		}
	}
	
	public void takeScreenCap(WebDriver driver, String path)   {
		
		
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File scrFile = scrShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(path + "fistPng_"+ java.time.LocalDateTime.now().toString() + ".png");

			try {
				FileHandler.copy(scrFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
	}
	
	public void type(By by, String value, String message) {
		
		try {
			driver.findElement(by).sendKeys(value);
			
			System.out.println();
			
			Reporter.log("Entering value : " + value + "at" + message + "field");
		} catch (Exception e) {
			
			Reporter.log(e.getMessage());
		}
		
	}
	

	@Test
	public void testScreenShot() {

		driver.get("https://demoqa.com/alerts");
		
		//By userName_txt = By.id(prop.getProperty("timeAlert_btn"));
		
		//type(userName_txt, "Shabhana", "User");
		
		By timer_btn = By.id(prop.getProperty("timeAlert_btn"));
		
		fluentWait(timer_btn);	
		
		takeScreenCap(driver, "D:\\CEP_WorkSpace\\TestNGProject\\ScreenShots\\");	

		driver.close();
	}

}
