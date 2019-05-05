package Testing;

import org.testng.annotations.Test;

import HomePage.DomesticIntenationalFlight;
import HomePage.Holidays;
import HomePage.Hotels;
import HomePage.HotelsByCity;
import HomePage.ListAssert;
import ScreenshotPackage.Screenshot;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class AllTest {
   
	public WebDriver driver;
	String url;
	ListAssert li;
	DomesticIntenationalFlight di;
	Holidays hl;
	Hotels ht;
	HotelsByCity hc;
	
	@BeforeClass(alwaysRun=true)
	public void setUp() {
		driver=new ChromeDriver();
		url="https://www.yatra.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(url);
	}

	@Test(groups={"listAssert"})
	public void testOriginCity() throws IOException, InterruptedException {
		li=new ListAssert(driver);
		li.clickOriginCity("nagpur");
		Thread.sleep(2000);
		li.arrival("mumbai");
		Thread.sleep(2000);
		li.departDate();
		Thread.sleep(2000);
		li.returnDate();
	}
	
	@Test(groups={"domInter"})
	public void testDomesticIntenationalFlight() throws InterruptedException{ 
		di=new DomesticIntenationalFlight(driver);
		di.clickDomInter();
		Thread.sleep(2000);
		di.windowHandle();
		Thread.sleep(2000);
	}
	
	@Test(groups={"holidays"})
	public void testHolidays() throws InterruptedException{ 
		hl=new Holidays(driver);
		hl.clickHolidayIcon();
		Thread.sleep(2000);
		hl.from("Pune");
		hl.to("Nagpur");
		Thread.sleep(2000);
		hl.travelMonth();
	}

	@Test(groups={"hotels"},priority=1)
	public void testHotels() throws InterruptedException{ 
		ht=new Hotels(driver);
		ht.clickHotel();
		Thread.sleep(2000);
		ht.firstField("The Oberoi");
	}
	
	@Test(groups={"hotels"},priority=2)
	public void testHotelList() throws InterruptedException{ 
		ht=new Hotels(driver);
		ht.hotelsList();
		Thread.sleep(2000);
		ht.clickOberoi();
		ht.arrivalDate();
		Thread.sleep(2000);
		ht.checkoutDate();
		ht.roomCapacity();
		ht.search();
		ht.info();
		ht.imgCarousel();
	}
	
	@Test(groups={"hotelcity"})
	public void testHotelCity() throws InterruptedException{ 
	   hc=new HotelsByCity(driver);
	   Thread.sleep(2000);
	   hc.frame1();
	   hc.clickHotel();
	   Thread.sleep(2000);
	   hc.city("Lonavala");
	   hc.lonavalaList();
	   Thread.sleep(2000);
	   hc.selectCity();
	   hc.departDate();
	   Thread.sleep(2000);
	   hc.toggleBtn();
	   hc.checkIn();
	   hc.checkOutDate();
	   hc.checkOutTime();
	   hc.room();
	   hc.childAge();
	   hc.search();
	   hc.frame();
	   hc.closeImage();
	  // hc.timeList();
	}
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result) throws Exception {
		if(ITestResult.FAILURE==result.getStatus()){ 
			Screenshot.getSnapshot(driver, result);
		}
	}

	@AfterClass(alwaysRun=true)
	public void getUp() throws Exception {
		Thread.sleep(2000);
		driver.quit();
	}

}
