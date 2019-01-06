package TestPack;

import org.testng.annotations.Test;

import Holidays.MakeMyTrip.Screenshot;
import Holidays.MakeMyTrip.SearchFlights;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class TestClass {
	public WebDriver driver;
	String baseUrl;
    SearchFlights sf;
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		driver = new ChromeDriver();
		baseUrl = "https://www.makemytrip.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test(groups="search")
	public void testSearchFilter() throws Exception {
      sf=new SearchFlights(driver);
      sf.frame();
     sf.fromCity();
      //sf.fromCityList();
      sf.departCity();
     // sf.listArrivalCity();
      sf.arrivalCity("pune");
      sf.departDate();
      sf.arrivalDateField();
     
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			Screenshot.takeSnapShot(driver, result);
		}

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

}
