package gen;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class HandlerDemo {
  public WebDriver driver;
  String baseUrl;
  
  @BeforeClass
  public void beforeClass() {
	  driver= new ChromeDriver();
	  baseUrl="http://naukri.com";
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  driver.get(baseUrl);
	  
  }
  
  @Test
  public void testPopup() {
	  String parent=driver.getWindowHandle();
	  Set<String>handles=driver.getWindowHandles();
	  Iterator<String>iterator=handles.iterator();
	  while(iterator.hasNext()){ 
		 String subWindow=iterator.next();
		// Here we will compare if parent window is not equal to child window then we will close
		  if(!parent.equalsIgnoreCase(subWindow)){ 
			  driver.switchTo().window(subWindow);
			  System.out.println("The name of child window is: "+driver.switchTo().window(subWindow).getTitle());
			  driver.close();
		  }
	  }
	  driver.switchTo().window(parent);
  }
  
  @AfterClass
  public void afterClass() {
  }

}
