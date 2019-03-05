package windowsAuthenticationPackage;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class WindowAuthentication {
   public WebDriver driver;
   String url;
   
  @BeforeClass
  public void beforeClass() {
	  driver=new FirefoxDriver();
	 //url="http://engprod-charter.net";
	  url="http://rsyspedia.india.rsystems.com";
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
  }

  @Test
  public void testWindowAuthentication() throws IOException {
	  Runtime.getRuntime().exec("C:\\Users\\Shubham\\workspace\\Yatra\\scripts\\windowAuthenticationHandling.exe");
	  driver.get(url);
  }
  
  @AfterClass
  public void afterClass() throws InterruptedException {
	 Thread.sleep(2000);
	  driver.quit();
  }

}
