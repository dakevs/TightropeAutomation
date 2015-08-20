package automationFramework;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Serp34Check {


	private static WebDriver driver = null;
	
	public static void main(String[] args) {
		
		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        //Launch the TestURL -- !!! This must be changed prior to adding script to story
        driver.get("http://staging.search.us.com/serp?s=0&filter_level=1&k=cars&guid=&action=homepage_search&parent_action=homepage_search&serpv=35&i=&aid=&campaign=&pub=3"); 
      
        
        //Output list of navigation links found within the page
        System.out.println("These are the links found within the SERP's Navigation bar:");
        WebElement navBar = driver.findElement(By.id("topnav"));
        List<WebElement> navigationLinks = navBar.findElements(By.tagName("li"));
        int navLinksListSize = navigationLinks.size();
        for(int i=0; i<navLinksListSize; i++)	{
       	 String sValue = navigationLinks.get(i).getText();
       	 System.out.println(sValue);
        }
                       
        //check for pricegrabber feed
        try
        {
        	WebElement priceGrabber = driver.findElement(By.xpath("//div[contains(@class, 'pricegrabber_cont_block')]"));
        	if(priceGrabber != null)
        		System.out.println("Pricegrabber feed is Present");
        }catch(Exception e){
        	System.out.println("Pricegrabber feed is Absent");
        }
       
        //check for offers.com feed on sidebar
        try
        {
        	WebElement offersSidebar = driver.findElement(By.xpath("//div[contains(@class, 'offers_cont_block')]"));
        	if(offersSidebar != null)	    
        		System.out.println("Offers.com sidebar feed is Present");
        }catch (Exception e){
        	System.out.println("Offers.com sidebar feed is Absent");
        	}
        
        
        //check for wikipedia block
        try
        {
        	WebElement wikiBlock = driver.findElement(By.xpath("//div[contains(@class, 'wiki_cont_block')]"));
            if(wikiBlock != null)
            	System.out.println("Wikipedia.com sidebar feed is Present");
        }catch (Exception e){
        	System.out.println("Wikipedia.com sidebar feed is Absent");
        	}
        
      //check for social icons
        try
        {
        	WebElement socialIcons = driver.findElement(By.xpath("//div[contains(@id, 'socialattach')]"));
            if(socialIcons != null)
            	System.out.println("Social icons sidebar feed is Present");
        }catch (Exception e){
        	System.out.println("Social icons sidebar feed is Absent");
        	}
        
            // Close the driver
               driver.quit();
        		
	}

}
