package datadriven;

import org.testng.annotations.Test;

import utilities.Constants;
import utilities.ExcelUtility;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class UsingExcel {
	private WebDriver driver;
  
  @BeforeClass
  public void beforeClass() throws Exception {
	  System.setProperty("webdriver.gecko.driver","G:\\SW testing online\\jar file\\Geckodriver\\geckodriver.exe");
      driver= new FirefoxDriver();
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.get(Constants.URL);
   // Tell the code about the location of Excel file
   		ExcelUtility.setExcelFile(Constants.File_Path + Constants.File_Name, "LoginTests");
  }
  
  @DataProvider(name = "loginData")
	public Object[][] dataProvider() {
		Object[][] testData = ExcelUtility.getTestData("Invalid_Login");
		return testData;
	}
  
  @Test(dataProvider="loginData")
  public void testUsingExcel(String username, String password) throws Exception {
		// Click login button
		driver.findElement(By.xpath("//a[@title='My Account']")).click();
		Thread.sleep(2000);
		// Enter username
		driver.findElement(By.id("log")).sendKeys(username);
		// Enter password
		driver.findElement(By.id("pwd")).sendKeys(password);
		// Click Login button
		driver.findElement(By.xpath("//input[@id='login']")).click();
		Thread.sleep(2000);
		
		// Find if error messages exist
		boolean result = driver.findElements(By.xpath("//form[@id='ajax_loginform']//p[@class='response']")).size() != 0;
		Assert.assertTrue(result);
	}
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
