package automationFramework;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class LandingPageChecks {



	private static WebDriver driver = null;
	
	public static void main(String[] args) throws IOException {
		
		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        File urlList = new File("C:\\Users\\kevin.zagala\\My Documents\\csv\\test.csv");
        FileReader urlListReader = new FileReader(urlList);
        BufferedReader urlBufferedReader = new BufferedReader(urlListReader);
        ArrayList<String> urls = new ArrayList<String>();
             
        try {
            String url;
            int urlNumber = 1;
            
            while ((url = urlBufferedReader.readLine()) != null) 
            {
            	System.out.println("");
            	System.out.println("test url" + " " + urlNumber + ": " + url);
            	driver.get(url);
            	urlNumber++;
            	
            	// checking for meta tag keywords
        		try {
        			// System.out.println("These are the meta tag keywords found within the page source:");
        			WebElement we = driver.findElement(By
        					.xpath("//meta[@name='keywords']"));
        			String metaTags = we.getAttribute("content");
        			System.out.println(metaTags);
        		} catch (Exception e) {
        			System.out.println("Meta tag keywords are Absent");
        		}
        		
        		// checking for chrome store weblink
        		try {
        			// System.out.println("These are the meta tag keywords found within the page source:");
        			WebElement we = driver.findElement(By
        					.xpath("//link[@rel='chrome-webstore-item']"));
        			String chromestoreLink = we.getAttribute("href");
        			System.out.println(chromestoreLink);
        		} catch (Exception e) {
        			System.out.println("Chrome store link is absent");
        		}
            	
        		
        		//Google Tag Manager check
        		
        		//bring page source into a variable
        		 String pageSource = driver.getPageSource();
                 if (pageSource.contains("www.googletagmanager.com"))
                 {
                	 System.out.println("Google Tag Manager pixel found");
                 }
                 else
                 {
                	 System.out.println("Google Tag Manager pixel NOT found");
                 }
                 
                 
                 
            	try {
            		Thread.sleep(2500);                 //1000 milliseconds is one second.
            	} catch(InterruptedException ex) {
            		Thread.currentThread().interrupt();	
            	}

            
            	
            }
            
        }
            
            finally {
            if (urlListReader != null) {
            	urlListReader.close();
            }
            

            if (urlBufferedReader != null) {
            	urlBufferedReader.close();
            }
         }
	
    
	}


}