package automationFramework;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SerpCheck {

	private static WebDriver driver = null;

	private static void checkElement(String xPath, String message) {
		try {
			WebElement we = driver.findElement(By.xpath(xPath));
			if (we != null)
				System.out.println(message + " is Present");
		} catch (Exception e) {
			System.out.println(message + " is Absent");
		}
	}

	public static void main(String[] args) {

		// Create a new instance of the Firefox driver
		driver = new FirefoxDriver();

		// Put a Implicit wait, this means that any search for elements on the
		// page could take the time the implicit wait is set for before throwing
		// exception
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Launch the TestURL -- !!! This must be changed prior to adding script
		// to story
		String testURL = "http://search.us.com/serp?s=0&serpv=35&campaign=&pub=&i=&parent_action=homepage_search&action=homepage_search&k=hotels";
		driver.get(testURL);
		System.out.println("This is the test URL: " + testURL);

		// Output list of navigation links found within the page
		System.out
				.println("These are the links found within the SERP's Navigation bar:");
		WebElement navBar = driver.findElement(By.id("topnav"));
		List<WebElement> navigationLinks = navBar
				.findElements(By.tagName("li"));
		int navLinksListSize = navigationLinks.size();
		for (int i = 0; i < navLinksListSize; i++) {
			String sValue = navigationLinks.get(i).getText();
			System.out.println(sValue);
		}

		// check for pricegrabber feed

		checkElement("//div[contains(@class, 'pricegrabber_cont_block')]",
				"Pricegrabber feed");
		checkElement("//div[contains(@class, 'offers_cont_block')]",
				"Offers.com sidebar feed");

		// check for wikipedia block
		try {
			WebElement wikiBlock = driver.findElement(By
					.xpath("//div[contains(@class, 'wiki_cont_block')]"));
			if (wikiBlock != null)
				System.out.println("Wikipedia.com sidebar feed is Present");
		} catch (Exception e) {
			System.out.println("Wikipedia.com sidebar feed is Absent");
		}

		// check for related searches
		try {
			WebElement relatedSearches = driver.findElement(By
					.xpath("//div[contains(@class, 'relatedsearch')]"));
			if (relatedSearches != null)
				System.out.println("Related searches are Present");
		} catch (Exception e) {
			System.out.println("Related searches are Absent");
		}

		// check for social icons
		try {
			WebElement socialIcons = driver.findElement(By
					.xpath("//div[contains(@id, 'socialattach')]"));
			if (socialIcons != null)
				System.out.println("Social icons are Present");
		} catch (Exception e) {
			System.out.println("Social icons are Absent");
		}

		// Close the driver
		driver.quit();

	}

}
