package automationFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OpenListOfUrlsChrome {

	
	public static void main(String[] args) throws IOException {
		
		// create the Chrome webdriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\kevin.zagala\\workspace\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
				
        File urlList = new File("C:\\Users\\kevin.zagala\\My Documents\\csv\\test.csv");
        FileReader urlListReader = new FileReader(urlList);
        BufferedReader urlBufferedReader = new BufferedReader(urlListReader);
        //ArrayList<String> urls = new ArrayList<String>();
             
        try {
            String url;
            int urlNumber = 1;
            
            while ((url = urlBufferedReader.readLine()) != null) 
            {
            	System.out.println("test url" + " " + urlNumber + ": " + url);
            	driver.get(url);
            	urlNumber++;
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
