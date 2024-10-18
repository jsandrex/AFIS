package com.example.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class GoogleSampleTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {

    	//Set up Chrome Driver
		WebDriverManager.chromedriver().setup();
		//Create Chrome Object
		driver = new ChromeDriver();
        // Initialize the Chrome WebDriver
		
        // Open Google
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }
    
    @Test
    public void googleSearchTest() {


        // Verify the title contains "Google"
        String title = driver.getTitle();
        assert title.contains("Google") : "Title does not contain 'Google'";
    }

    @AfterTest
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
	

