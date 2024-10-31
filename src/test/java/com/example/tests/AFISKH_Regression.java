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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AFISKH_Regression {

    WebDriver driver;

    @BeforeTest
    public void setup() {

    	InternetExplorerOptions options = new InternetExplorerOptions();
    	options.ignoreZoomSettings();
    	options.withInitialBrowserUrl("http://10.100.100.27:9780/AFSCambodiaLatest/Login.htm");
    	driver = new InternetExplorerDriver(options);
    	driver.manage().window().maximize();

//        // Navigate to the URL that requires IE mode
//        driver.get("http://10.100.100.27:9780/AFSCambodiaLatest/Login.htm");
//        driver.manage().window().maximize();
    }
    @Test
    public void AFISKH() throws InterruptedException, IOException{
    //Login Page
    driver.findElement(By.xpath("//*[@id=\"USERID\"]")).sendKeys("SOKMENG");

    Thread.sleep(500);
    // Use JavaScript to set the password
    WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"PASSWORD\"]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].value='SYSADMIN1';", passwordField);

    // Wait again to ensure the input is registered
    Thread.sleep(500);

    // Focus back on the password field to avoid any loss of input
    passwordField.click();
 
    
    //HOME PAGE
    driver.findElement(By.xpath("//*[@id=\"sysLoginWrap\"]/div[2]/input[3]")).click();
    driver.findElement(By.xpath("//*[@id=\"menuToggleBtnWrap\"]/div[1]")).click();
    driver.findElement(By.className("toggleBtnTitle")).click();
//	driver.findElement(By.cssSelector("#navigation > li.last")).click(); >> Temporary since cannot find in submenu
    driver.get("http://10.100.100.27:9780/AFSCambodiaLatest/DealerUpload.htm");
    
    //IMAGE UPLAD
    driver.findElement(By.id("FILENAME_0")).sendKeys("C:\\Users\\jsramos\\Pictures\\Signature.png");
    driver.findElement(By.id("FILENAME_1")).sendKeys("C:\\Users\\jsramos\\Pictures\\Signature.png");
    driver.findElement(By.xpath("/html/body/div[4]/form/div/div/input[1]")).click();
    
 // Select "COPY OF BUSINESS LICENSE" using JavaScript
    WebElement imageTypeDropdown = driver.findElement(By.id("IMAGETYPE_1"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", imageTypeDropdown);
    Thread.sleep(500);
    ((JavascriptExecutor) driver).executeScript("arguments[0].value='141';", imageTypeDropdown);

 // Select "APPLICANT PHOTO"
    WebElement imageTypeDropdown2 = driver.findElement(By.id("IMAGETYPE_2"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", imageTypeDropdown2); 
    Thread.sleep(500);
    ((JavascriptExecutor) driver).executeScript("arguments[0].value='136';", imageTypeDropdown2); 
 // Dropdown Type of Application
    WebElement TypeOfAppl = driver.findElement(By.xpath("//*[@id=\\\"imgBand\\\"]/table/tbody/tr/td[1]/table/tbody/tr[4]/td/input[1]"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", TypeOfAppl); 
    Thread.sleep(500);
    ((JavascriptExecutor) driver).executeScript("arguments[0].id='APPLICATIONTYPE';", TypeOfAppl);
    
    driver.findElement(By.xpath("//*[@id=\"imgBand\"]/table/tbody/tr/td[1]/table/tbody/tr[4]/td/input[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"imgBand\"]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/input[1]")).click();
    Select TypeOfApplz = new Select(driver.findElement(By.id("APPLICATIONTYPE")));
    TypeOfApplz.selectByVisibleText("1 : REGULAR");
    driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/table/tbody/tr[2]/td[2]/input[2]")).click();
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("d");
    String currentDay = currentDate.format(dayFormat);
    driver.findElement(By.xpath("//td[normalize-space()='" + currentDay + "']")).click();
    Select TypeOfLoan = new Select(driver.findElement(By.id("LOANCODE")));
    TypeOfLoan.selectByVisibleText("GHP");
    Select TypeOfLoan1 = new Select(driver.findElement(By.id("CURRENCY")));
    TypeOfLoan1.selectByVisibleText("USD");
    Select IDCardNo = new Select(driver.findElement(By.id("IDCARDTYPE")));
    IDCardNo.selectByVisibleText("1 : ID CARD");
    driver.findElement(By.xpath("//*[@id=\"IDCARDNO\"]")).sendKeys("1122334455");
    driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/table/tbody/tr[2]/td[7]/input")).sendKeys("JAMES SELENIUM");
    driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/div[1]/input")).click();
    driver.findElement(By.xpath("/html/body/div[4]/form/div[3]/div[2]/input[1]")).click();
    
 
    
//    @AfterTest
//   public void tearDown() {
//        // Close the browser
//        if (driver != null) {`
//            driver.quit();
       // }
    //}
}
}
	