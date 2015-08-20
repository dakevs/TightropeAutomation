package automationFramework;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class UniversityOfPhoenix {

	private static WebDriver driver = null;

	public static void main(String[] args) throws IOException, InterruptedException {

		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.get("http://staging.universityofphoenixinfo.com/c/uop-education/index.php?");
        
        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);       
        
        //Story id, release date and server instance. Change this before each run to ensure that a unique email address is used
        String storyID, releaseDate, serverInstance, email;
        storyID = "98864178";
        releaseDate = "07132015";
        serverInstance = "staging";
        
        //used to select randomness
        Random rnd = new Random();
        
        //select random area of interest
        Select areaOfInterest = new Select(driver.findElement(By.name("custom3")));
        int indexAreaOfInterest = rnd.nextInt(areaOfInterest.getOptions().size() - 1);
        areaOfInterest.selectByIndex(indexAreaOfInterest++);
        
        driver.findElement(By.id("btn1")).click();

        //Randomly select between Online/Campus
        List<WebElement> radioCampus = driver.findElements(By.name("modality"));
		radioCampus.get(rnd.nextInt(radioCampus.size())).click();
		
        //ZIP code will pull a random value from a CSV file containing ZIP codes
        File zipFile = new File("c:\\zips.csv");
        FileReader zipFileReader = new FileReader(zipFile);
        BufferedReader zipBufferedReader = new BufferedReader(zipFileReader);
        ArrayList<String> zips = new ArrayList<String>();
             
        try {
            String zipLine;
            while ((zipLine = zipBufferedReader.readLine()) != null) {
                zips.add(zipLine);
            }
        } finally {
            if (zipFileReader != null) {
            	zipFileReader.close();
            }

            if (zipBufferedReader != null) {
            	zipBufferedReader.close();
            }
        }
      
        int zipIndex = (int)(zips.size() * Math.random());     
        String zipToString = "" + zips.get(zipIndex);
        driver.findElement(By.name("postalCode")).sendKeys(zipToString);
        Thread.sleep(2000);
        driver.findElement(By.id("btn4")).click();
        
        //Randomly select education Level
        Select educationLevel = new Select(driver.findElement(By.name("educationLevel")));
        int optionIndexLevel = rnd.nextInt(educationLevel.getOptions().size() - 3);
        educationLevel.selectByIndex(optionIndexLevel + 3);
		
        driver.findElement(By.id("btn3")).click();

        //randomly select between Mr./Ms.
        List<WebElement> salutation = driver.findElements(By.name("salutation"));
		salutation.get(rnd.nextInt(salutation.size())).click();
          
		//Send first name
        driver.findElement(By.name("firstName")).sendKeys("Test");
        
        //Send last name
        driver.findElement(By.name("lastName")).sendKeys("Lead");

        //Send email
        email = "kevin"+storyID+"-"+releaseDate+"-"+serverInstance+"-UoPinfo@education180.com";
        driver.findElement(By.name("emailAddress")).sendKeys(email);
        
        driver.findElement(By.id("btn5")).click();
        
        //Send address
        driver.findElement(By.name("address1")).sendKeys("123 main");
        
        //Randomly select from list of phone numbers
      
        //phone number will pull a random value from a CSV file containing phone #s
        File phoneNumberfile = new File("c:\\testphonenumbers.csv");
        FileReader phoneFileReader = new FileReader(phoneNumberfile);
        BufferedReader phoneBufferedReader = new BufferedReader(phoneFileReader);
        ArrayList<String> phoneNumbers = new ArrayList<String>();
        try {
            String phoneLine;
            while ((phoneLine = phoneBufferedReader.readLine()) != null) {
            	phoneNumbers.add(phoneLine);
            }
        } finally {
            if (phoneFileReader != null) {
            	phoneFileReader.close();
            }

            if (phoneBufferedReader != null) {
            	phoneBufferedReader.close();
            }
        }
        
        int phoneIndex = (int)(phoneNumbers.size() * Math.random());
        String phoneToString = "" + phoneNumbers.get(phoneIndex);
        driver.findElement(By.name("homePhone")).sendKeys(phoneToString);

        
        //randomly select between Yes/No for international credits
        List<WebElement> intlCredits = driver.findElements(By.id("credits2"));
        intlCredits.get(rnd.nextInt(intlCredits.size())).click();
        
        //Randomly select from military affiliation to exclude the first option "--Select--"
		Select military = new Select(driver.findElement(By.name("military")));
        int optionIndexMilitary = rnd.nextInt(military.getOptions().size() - 1);
        military.selectByIndex(optionIndexMilitary++);
        
        //randomly select between Yes/No for citizenship question
        List<WebElement> citizenship = driver.findElements(By.id("citizen2"));
        citizenship.get(rnd.nextInt(citizenship.size())).click();
      
	}

}
