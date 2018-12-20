package thirdPackage;

import org.testng.annotations.Test;

import utilities.ExcelUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ExcelDataProvider {
	private WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "G:\\SW testing online\\jar file\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.store.demoqa.com");
	}

	@Test(dataProvider = "test1data")
	public void testexcel(String username, String password) throws Exception {
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
		boolean result = driver.findElements(By.xpath("//form[@id='ajax_loginform']//p[@class='response']"))
				.size() != 0;
		Assert.assertTrue(result);
		System.out.println(result);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@DataProvider(name = "test1data")
	public Object[][] getData() {
		String excelPath = "C:\\Users\\Shubham\\workspace\\ApachePOI\\src\\utilities\\ExcelToDataProv.xlsx";
		Object data[][] = testData(excelPath, "Sheet1");

		return data;
	}

	public Object[][] testData(String excelPath, String sheetName) {
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object[][] data = new Object[rowCount - 1][colCount];
		// we used rowCount-1 bcoz we have 1st row as header row and we don't
		// want to read that row

		for (int i = 1; i < rowCount; i++) {
			//i=1 coz 1st row is header row and we don't want to read that row
			for (int j = 0; j < colCount; j++) {
				
				String cellData = excel.getCellDataString(i, j);
				//for retreiving data from excell cells
				
				data[i - 1][j] = cellData;
				//we r using i-1 coz in above for loop we used i=1, so it will start from 2nd row
				//but if we write i=1 in place of 1-1 then the loop will start from 3rd row
				//hence i-1 is used so that loop can start from 2nd row
			}
		}
		return data;
	}

}
