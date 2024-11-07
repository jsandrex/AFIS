package com.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AFISKH_Regression {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.withInitialBrowserUrl("http://10.100.100.27:9780/AFSCambodiaLatest/Login.htm");
        driver = new InternetExplorerDriver(options);
        driver.manage().window().maximize();
    }

    // Method to generate a random 10-digit number
    public String generateRandomIdCardNumber() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate a 10-digit number (digits between 0 and 9)
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10)); // Generate a digit between 0 and 9
        }

        return sb.toString();
    }

    @Test
    public void testLoginPage() throws InterruptedException {
        //<LOGIN PAGE>
        driver.findElement(By.xpath("//*[@id=\"USERID\"]")).sendKeys("SOKMENG");

        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"PASSWORD\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='SYSADMIN1';", passwordField);
        Thread.sleep(500);
        passwordField.click();
    }

    @Test(dependsOnMethods = "testLoginPage")
    public void testHomePage() {
        //<HOME PAGE>
        driver.findElement(By.xpath("//*[@id=\"sysLoginWrap\"]/div[2]/input[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"menuToggleBtnWrap\"]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[12]/a")).click();
    }

    @Test(dependsOnMethods = "testHomePage")
    public void testImageUpload() throws InterruptedException {
        //<IMAGE UPLOAD>
    	
        driver.findElement(By.id("FILENAME_0")).sendKeys("C:\\Users\\jsramos\\Pictures\\Signature.png");
        driver.findElement(By.id("FILENAME_1")).sendKeys("C:\\Users\\jsramos\\Pictures\\Signature.png");
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div/input[1]")).click();

        // Select "COPY OF BUSINESS LICENSE" using JavaScript
        WebElement imageTypeDropdown = driver.findElement(By.id("IMAGETYPE_1"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", imageTypeDropdown);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='1';", imageTypeDropdown);

        // Select "APPLICANT PHOTO"
        WebElement imageTypeDropdown2 = driver.findElement(By.id("IMAGETYPE_2"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", imageTypeDropdown2);
        Thread.sleep(500);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='0';", imageTypeDropdown2);

        driver.findElement(By.xpath("//*[@id=\"imgBand\"]/table/tbody/tr/td[1]/table/tbody/tr[4]/td/input[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"imgBand\"]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/input[1]")).click();

        // Type of application
        Select TypeOfApplz = new Select(driver.findElement(By.id("APPLICATIONTYPE")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='1';", TypeOfApplz);

        // Application date
        WebElement appldate = driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/table/tbody/tr[2]/td[2]/input[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", appldate);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("d");
        String currentDay = currentDate.format(dayFormat);
        driver.findElement(By.xpath("//td[normalize-space()='" + currentDay + "']")).click();

        Select loancode = new Select(driver.findElement(By.id("LOANCODE")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='GHP';", loancode);

        Select currency = new Select(driver.findElement(By.id("CURRENCY")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='USD';", currency);

        Select IDcardType = new Select(driver.findElement(By.id("IDCARDTYPE")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='1';", IDcardType);
        
     // Generate random ID card number and enter it
        String randomIdCardNumber = generateRandomIdCardNumber();
        driver.findElement(By.xpath("//*[@id=\"IDCARDNO\"]")).sendKeys(randomIdCardNumber);
        System.out.println("Random ID Card Number: " + randomIdCardNumber);
        
        driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/table/tbody/tr[2]/td[7]/input")).sendKeys("JAMES SELENIUM");
        driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/div[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/div[2]/input[1]")).click();

        // After clicking submit and waiting for the response message
        String successMessage = driver.findElement(By.xpath("//*[contains(text(), 'New Application number')]")).getText();
        String applicationNumber = successMessage.replaceAll(".*New Application number : ([\\d-]+).*", "$1");
        System.out.println("Application Number: " + applicationNumber);
    }

    @Test(dependsOnMethods = "testImageUpload")
    public void testHomePageToDE1() {
        //<HOME PAGE GO TO DE1>
        driver.findElement(By.xpath("//*[@id=\"logoLink\"]/img")).click();
        driver.findElement(By.xpath("//*[@id=\"menuToggleBtnWrap\"]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[3]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[3]/ul/li[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"navigation\"]/li[3]/ul/li[1]/ul/li[3]/a")).click();
    }
    
    

    //@AfterTest
    //public void tearDown() {
    //    if (driver != null) {
    //        driver.quit();
    //    }
    //}
}
