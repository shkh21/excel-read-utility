package BrokenImagePackage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class TestBrokenImages {
   public WebDriver driver;
 
   String baseUrl;
   CheckBrokenImages im;
   
  @BeforeClass
  public void beforeClass() {
	  driver=new ChromeDriver();
	  baseUrl="https://the-internet.herokuapp.com/broken_images";
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  driver.get(baseUrl);
  }
  
  @Test
  public void testImages() {
	  im=new CheckBrokenImages(driver);
	  im.brokenImages();
	  im.verifyURLStatus(baseUrl);
	  
  }
 

  @AfterClass
  public void afterClass() throws InterruptedException {
	  Thread.sleep(2000);
	  driver.quit();
  }

}
