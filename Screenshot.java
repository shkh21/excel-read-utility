package Holidays.MakeMyTrip;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class Screenshot {
	public WebDriver driver;
	public static void takeSnapShot(WebDriver driver, ITestResult result) throws Exception {

		
		  try { 
			  TakesScreenshot ts=(TakesScreenshot)driver;
		 
		 // Call method to capture screenshot File
			 File source=ts.getScreenshotAs(OutputType.FILE);
		 
		  // Copy files to specific location here it will save all screenshot
		    //in our project home directory and  result.getName() will return
		  // name of test case so that screenshot name will be same
		  FileUtils.copyFile(source, new
		  File("./Screenshots/"+result.getName()+".png"));
		 
		 System.out.println("Screenshot taken"); } catch (Exception e) {
		  
		  System.out.println("Exception while taking screenshot "
		  +e.getMessage()); }
		  
	}
}
