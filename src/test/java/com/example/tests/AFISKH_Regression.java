package com.example.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AFISKH_Regression {

    WebDriver driver;

    @BeforeTest
    public void setup() {

        // Set up Edge Driver
        WebDriverManager.edgedriver().setup();

        // Create EdgeOptions to enable IE mode
        EdgeOptions edgeOptions = new EdgeOptions();

        // Enable IE Mode by adding appropriate arguments
        edgeOptions.addArguments("inprivate"); // Open in InPrivate mode (optional)
        edgeOptions.addArguments("ie-mode-force"); // Force IE Mode for this session
        edgeOptions.addArguments("enable-features=msEdgeIECompat"); // Enable IE compatibility mode

        // Initialize the EdgeDriver with the EdgeOptions
        driver = new EdgeDriver(edgeOptions);

        // Navigate to the URL that requires IE mode
        driver.get("http://10.100.100.27:9780/AFSCambodiaLatest/Login.htm");
        driver.manage().window().maximize();
    }


    
    @Test
    public void AFISKH() {
    driver.findElement(By.xpath("//*[@id=\"USERID\"]")).sendKeys("SOKMENG");
    driver.findElement(By.xpath	("//*[@id=\"PASSWORD\"]")).sendKeys("SYSADMIN1");
    }

//    @AfterTest
//   public void tearDown() {
//        // Close the browser
//        if (driver != null) {
//            driver.quit();
       // }
    //}
}
	

