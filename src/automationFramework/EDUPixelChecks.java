package automationFramework;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.lang3.StringUtils;
public class EDUPixelChecks {

	/**
	 * @param args
	 */
	
	 private static WebDriver driver = null;
		
	public static void main(String[] args) throws IOException {		
		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

       //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

       //Launch the TestURL -- !!! This must be changed prior to adding script to story
        String testURL = "http://staging.online.education180.com/info/edu180-base/";
        driver.get(testURL);
        System.out.println("This is the test url: "+ testURL);
        
        //Store page source in variable
        String pageSource = driver.getPageSource();
        System.out.println("*** Tracking pixel checks *** ");
        
        
        //Leadidscript check
        if (StringUtils.containsIgnoreCase(pageSource, "leadidscript"))		
        {
        	System.out.println("leadidscript pixel found");
        }
        else
        {
        	System.out.println("leadidscript pixel NOT found");
        }
        
        //bfg check
        if (StringUtils.containsIgnoreCase(pageSource, "bfg01"))		
        {
        	System.out.println("Bfg pixel found");
        }
        else
        {
        	System.out.println("Bfg pixel NOT found");
        }
        
        //Google Analytics check
        if (StringUtils.containsIgnoreCase(pageSource, "UA-"))		
        {
        	System.out.println("google analytics pixel found");
        }
        else
        {
        	System.out.println("Google analytics pixel NOT found");
        }
        
        //Google conversion check
        if (StringUtils.containsIgnoreCase(pageSource, "google_conversion_id"))		
        {
        	System.out.println("Google conversion pixel found");
        }
        else
        {
        	System.out.println("Google conversion pixel NOT found");
        }
        
        
        //crazyegg check
      /*  int cloudfCount = StringUtils.countMatches(pageSource, "cloudfront.net");
        System.out.println("There are " + cloudfCount + " instances of cloudf text found within the page source.");  
       
        PrintWriter out = new PrintWriter("pagesource.txt");
        out.println(pageSource);*/
        
      //  String pageSource1 = driver.getPageSource();
        //int cloudfCount = StringUtils.countMatches(pageSource1, "cloudfront.net");
        //System.out.println("There are " + cloudfCount + " instances of cloudf text found within the page source.");  
       
        //PrintWriter out = new PrintWriter("pagesource.txt");
        //out.println(pageSource1);
      
        // Close the driver
        //driver.quit();
      //Adroll check

        /*if (StringUtils.containsIgnoreCase(pageSource, "adroll"))		
        {
        	System.out.println("adroll text found");
        }
        else
        {
        	System.out.println("adroll text NOT found");
        }*/


	}

}
