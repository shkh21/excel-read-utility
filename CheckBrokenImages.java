package BrokenImagePackage;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBrokenImages {
	public  WebDriver driver;
	public int invalidLinksCount;
	
	public CheckBrokenImages(WebDriver driver){ 
		this.driver=driver;
	}
	
	public  void brokenImages() {
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
}
