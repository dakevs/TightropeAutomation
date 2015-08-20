package automationFramework;
 
    import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class UoPVanillaTests {
 
    private static WebDriver driver = null;
 
    public static void main(String[] args) throws InterruptedException {
 
        // Create a new instance of the Firefox driver
         driver = new FirefoxDriver();
 
        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
         driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
 
        //Launch the TestURL -- !!! This must be changed prior to adding script to story
         driver.get("http://staging.universityofphoenixinfo.com/uop-test-2-2/?source=QA&c=campaign&k=kevin");
 
         //define the Area of Interests list element 
         Select areasOfInterest = new Select(driver.findElement(By.name("custom3")));
        
         //Verify the selections for Area of Interest list
         System.out.println("*** Listing Areas of Interest*** ");
         List<WebElement> areasOfInterestList = areasOfInterest.getOptions();
                  int areasOfInterestListSize = areasOfInterestList.size();
         for(int i=0; i<areasOfInterestListSize; i++)	{
        	 String sValue = areasOfInterest.getOptions().get(i).getText();
        	 System.out.println(sValue);
         }
               
         //Select an Area of Interest
         areasOfInterest.selectByVisibleText("Criminal Justice");
         driver.findElement(By.id("btn1")).click();
         
         //Select Online 
         WebElement onlineButton = driver.findElement(By.id("online"));         
         onlineButton.click();
         
         //enter zip code
         WebElement inputZipCode = driver.findElement(By.name("postalCode"));
         inputZipCode.sendKeys("94590");
         Thread.sleep(2000);
         driver.findElement(By.id("btn4")).click();
         
                 
         //define the Education Levels 
         Select educationLevel = new Select(driver.findElement(By.name("educationLevel")));
         //Verify the selections for Education levels list
         System.out.println("*** Listing Education levels*** ");
         List<WebElement> educationLevelsList = educationLevel.getOptions();
         int educationLevelsListSize = educationLevelsList.size();
         for(int i=0; i<educationLevelsListSize; i++)	{
        	 String educationOption = educationLevel.getOptions().get(i).getText();
        	 System.out.println(educationOption);
         }
         //Select an Education Level
         educationLevel.selectByVisibleText("Have GED");
         driver.findElement(By.id("btn3")).click();

         //Input Lead data  
         WebElement salutationBtn = driver.findElement(By.id("mr"));         
         salutationBtn.click();
         WebElement inputFirstName = driver.findElement(By.name("firstName"));
         inputFirstName.sendKeys("Kevin");
         WebElement inputLastName = driver.findElement(By.name("lastName"));
         inputLastName.sendKeys("Zagala");
         WebElement inputEmail = driver.findElement(By.name("emailAddress"));
         inputEmail.sendKeys("Kevin@education180.com");
         driver.findElement(By.id("btn5")).click();
         
         //Final submit page
         WebElement inputStreetAddress = driver.findElement(By.name("address1"));
         inputStreetAddress.sendKeys("123 main");
         WebElement inputPhoneNumber = driver.findElement(By.name("homePhone"));
         inputPhoneNumber.sendKeys("7077125728");
         
         //define the military affiliation list 
         Select militaryAffiliation = new Select(driver.findElement(By.name("military")));
        
         //Verify the selections for Area of Interest list
         System.out.println("*** Listing Military Affiliations *** ");
         List<WebElement> militaryList = militaryAffiliation.getOptions();
         int militaryListSize = militaryList.size();
         System.out.println(militaryListSize);
         for(int i=0; i<militaryListSize; i++)	{
        	 String militaryOption = militaryAffiliation.getOptions().get(i).getText();
        	 System.out.println(militaryOption);
         }
         
         //Store page source in variable for Pixel Checks
         String pageSource = driver.getPageSource();
         System.out.println("*** Tracking pixel checks *** ");
        
         //bfg check
         if (pageSource.contains("p.universityofphoenixinfo.com"))
         {
        	 System.out.println("Bfg pixel found");
         }
         else
         {
        	 System.out.println("Bfg pixel NOT found");
         }
         //Google remarketing check
         if (pageSource.contains("conversion"))
         {
        	 System.out.println("Google remarking pixel found");
         }
         else
         {
        	 System.out.println("Google remarketing NOT found");
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
         //Adroll check
         if (pageSource.contains("adroll"))
         {
        	 System.out.println("adroll pixel found");
         }
         else
         {
        	 System.out.println("adroll pixel NOT found");
         }
         
         
         //crazyegg check
         //for this check, will have to declare a variable to keep track of # of instances of cloudf is found
        // System.out.println(StringUtils.countMatches(pageSource, "cloudfront"));
         
      
    	 
         //Check all Links
         System.out.println("*** Beginning dead link check now*** ");
         List<WebElement> links = driver.findElements(By.tagName("a"));
         System.out.println(links.size());
         for (int i = 0; i<links.size(); i++)
         {
  
             System.out.println(links.get(i).getText());
  
         }
         
         //driver.findElement(By.id("btn6")).click();
       
    }
    		
          
         // Close the driver
         //driver.quit();
      }
    