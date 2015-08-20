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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenListOfURLs {

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
            while ((url = urlBufferedReader.readLine()) != null) 
            {
                //urls.add(url);
            	//System.out.println("test url: " + url);
            	driver.get(url);
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
	
    
        //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");       
        //driver.switchTo().window(urls.get(0));
	}

	}

	
	


	
        
      

      
	