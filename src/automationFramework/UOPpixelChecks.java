package automationFramework;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;



public class UOPpixelChecks {

	/**
	 * @param args
	 */
	  private static WebDriver driver = null;
	
	
	public static void main(String[] args) {

		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

       //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

       //Launch the TestURL -- !!! This must be changed prior to adding script to story
        String testURL = "http://staging.universityofphoenixinfo.com/d/uop-learning/?s=QA&c=test&k=kevin";
        System.out.println("This is the test url:" + testURL);
        driver.get(testURL);
                
        //Store page source in variable
        String pageSource = driver.getPageSource();
        System.out.println("*** UOP tracking pixel checks *** ");
        
        //bfg check
        if (pageSource.contains("p.universityofphoenixinfo.com"))
        {
       	 System.out.println("Bfg pixel found");
        }
        else
        {
       	 System.out.println("Bfg pixel NOT found");
        }
        
        //Google Conversion Pixel check
        if (pageSource.contains("google_conversion_id"))
        {
       	 System.out.println("Google Conversion Pixel found");
        }
        else
        {
       	 System.out.println("Google Conversion Pixel  NOT found");
        }
        
        //Google Analytics check
        if (pageSource.contains("UA-"))
        {
       	 System.out.println("google analytics pixel found");
        }
        else
        {
       	 System.out.println("Google analytics pixel NOT found");
        }
        
        //optimizely check
        if (pageSource.contains("optimizely"))
        {
       	 System.out.println("optimizely pixel found");
        }
        else
        {
       	 System.out.println("Optimizely pixel NOT found");
        }
              
        
        //Create javascript executor object
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//Create mindsparkobject, execute the mindspark javascript code, and store that object in a variable
		Object userProgram = (Object) js.executeScript("return jQuery('#progContainer').val();");
		//Output the mindspark object URL
		System.out.println("This is the user's program: " + userProgram); //output object attributes
		}
		
		
		
		/* we can't automate the crazyegg check because of the m
         * ethod we use to verify the pixel.
         * currently, we view the page source, search for term "cloudf", and verify that there are 2 instances of it appearing.
         * however, if we were to perform a search for that same term within the pageSource String created by
         *         String pageSource = driver.getPageSource();
         * we get 5 instances. It's unknown why that is, but mostly the javascript pixel functions differently during page runtime versus page load.  
         */
     //   driver.quit();
        
	}


