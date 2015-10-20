package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



public class BrowserstackTest {

	
	public static final String USERNAME = "browserstack178";
	public static final String AUTOMATE_KEY = "z14A35BazqGjknyx9kq9";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	
	
	public static void main(String[] args) throws MalformedURLException, IOException {

		
		//Set the browser we want to check the URLs in
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "IE");
		caps.setCapability("browser_version", "7.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "XP");
		caps.setCapability("browserstack.debug", "true");
	
		
		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	
		//create the file readers
        File urlList = new File("C:\\Users\\kevin.zagala\\My Documents\\csv\\test.csv");
        FileReader urlListReader = new FileReader(urlList);
        BufferedReader urlBufferedReader = new BufferedReader(urlListReader);
		
        try {
            String url;
            int urlNumber = 1;
             while ((url = urlBufferedReader.readLine()) != null) 
            {

               	System.out.println("Test url" + " " + urlNumber + ": " + url);
            	driver.get(url);
            	System.out.println("Page Title: " + driver.getTitle());
            	//***CODE SNIPPET TO TAKE A SCREENSHOT***
        		driver = new Augmenter().augment(driver);
        		((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        		System.out.println("Screenshot taken!");
        		/////////////////////////////////////////       		
        		System.out.println("");
        		urlNumber++;
        		
        		try {
        			System.out.println("These are the meta tag keywords found within the page source:");
        			WebElement we = driver.findElement(By
        					.xpath("//meta[@name='keywords']"));
        			String metaTags = we.getAttribute("content");
        			System.out.println(metaTags);
        			System.out.println();
        			} catch (Exception e) {
        				System.out.println("Meta tag keywords are Absent");
        				System.out.println();
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
	
   		
		driver.quit();
					
			}	        	
	}
