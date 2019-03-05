package Holidays.MakeMyTrip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;



public class ListAssert {
    public WebDriver driver;
    
    @FindBy(xpath="//input[@id='hp-widget__sfrom']")
    WebElement elementDestination;
    
    @FindBy(xpath="//ul[@id='ui-id-1']//child::li")
   List<WebElement> elementList;
    
 
    
    public ListAssert(WebDriver driver){ 
    	this.driver=driver;
    	PageFactory.initElements(driver,this);
    }
    
    public void frame() {
		driver.switchTo().frame("webpush-bubble");
		driver.findElement(By.id("deny")).click();
		driver.switchTo().defaultContent();
	}
    
    public void listDestination(){ 
    	elementDestination.click();
    	String[] listNames={"TOP CITIES","DEL","BOM","BLR","GOI","MAA","CCU","HYD","PNQ","AMD","COK","JAI","DXB","SIN","BKK","NYC","KUL","LON","HKG","DOH","CMB"};
    	int expected=listNames.length;
    	System.out.println(expected);
    	
    	int actual=elementList.size();
    	System.out.println(actual);
    	
      // Assert.assertEquals(actual, expected);
    	for(int i=0;i<expected;i++){ 
    		if(elementList.get(i).getText().contains(listNames[i])){ 
    			System.out.println("Matching value : "+elementList.get(i).getText()+" : : "+listNames[i]);
    		}
    		else{ 
    			System.out.println("Non Matching value : "+elementList.get(i).getText()+" : : "+listNames[i]);
    		}
    	}
    }
}
