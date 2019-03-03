package fileUpload;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class FileUploadWindowAutoIT {
   public WebDriver driver;
   String url;
   
	@BeforeClass
	public void beforeClass() {
		driver=new ChromeDriver();
		url="https://www.ilovepdf.com/word_to_pdf";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void testFileUpload() throws IOException, InterruptedException{
		WebElement elementUpload=driver.findElement(By.xpath("//a[@id='pickfiles']"));
		elementUpload.click();
		
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Shubham\\workspace\\Yatra\\scripts\\filleupload.exe");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='processTask']")).click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
