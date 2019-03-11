package Lnk;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckLin {
	 private WebDriver driver;
	  private String baseUrl;
	  private int invalidLinksCount;

	  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "G:\\SW testing online\\jar file\\chromedriver_win32\\chromedriver.exe");
		 driver = new ChromeDriver();
		 baseUrl="https://the-internet.herokuapp.com/broken_images";
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get(baseUrl);
  }
  @Test
  public void f() {
	  try {
			invalidLinksCount = 0;
			List<WebElement> anchorTagsList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of links are "
					+ anchorTagsList.size());
			for (WebElement anchorTagElement : anchorTagsList) {
				if (anchorTagElement != null) {
					String url = anchorTagElement.getAttribute("href");
					String link=anchorTagElement.getAttribute("src");
					System.out.println("Src is: "+link+" Url is: "+url);
					if (url != null && !url.contains("javascript")) {
						verifyURLStatus(url);
					} else {
						invalidLinksCount++;
					}
				}
			}

			System.out.println("Total no. of invalid links are "
					+ invalidLinksCount);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	  
  }
  
  public void verifyURLStatus(String baseUrl) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(baseUrl);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			////We can also check for 404 status code like response.getStatusLine().getStatusCode() == 404
			if (response.getStatusLine().getStatusCode() != 200)
				invalidLinksCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  @AfterClass
  public void afterClass() {
     driver.quit();
  }
}
