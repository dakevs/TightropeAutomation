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


public class HealthcareTech {
	
	private static WebDriver driver = null;

	public static void main(String[] args) throws IOException {

		

		
		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.get("http://alliedhealth.me/landing/healthtech_connect/healthcare_tech.php?");
        
        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);       
        
        
        //Story id, release date and server instance. Change this before each run to ensure that a unique email address is used
        String storyID, releaseDate, serverInstance, email;
        storyID = "95578976";
        releaseDate = "05282015";
        serverInstance = "server1";
        
        //used to select randomness
        Random rnd = new Random();
        
		//This page calls a javascript function called Firstblood.Iterator.forward(), which we must call, instead of "clicking" a button with an ID.
        //Create javascript executor object
		JavascriptExecutor js = (JavascriptExecutor) driver;
        
        
        //Send first name, last name, and select a random ZIP. Then hit continue
        driver.findElement(By.name("firstName")).sendKeys("Test");
        driver.findElement(By.name("lastName")).sendKeys("Lead");

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

        //Execute the function to simulate a button click
        js.executeScript("FirstBlood.Iterator.forward()");

		    
        //randomly select between Yes/No for citizenship question
        List<WebElement> citizenship = driver.findElements(By.id("citizen2"));
        citizenship.get(rnd.nextInt(citizenship.size())).click();
        
        //Randomly select from military affiliation to exclude the first option "--Select--"
		Select military = new Select(driver.findElement(By.name("military")));
        int optionIndexMilitary = rnd.nextInt(military.getOptions().size() - 1);
        military.selectByIndex(optionIndexMilitary++);
        
        //Randomly select from age range
        Select ageRange = new Select(driver.findElement(By.name("ageRange")));
        int optionIndexAge = rnd.nextInt(ageRange.getOptions().size() - 1);
        ageRange.selectByIndex(optionIndexAge++);
        //Execute the function to simulate a button click
        js.executeScript("FirstBlood.Iterator.forward()");

        //Randomly select a start date
        Select startDate = new Select(driver.findElement(By.name("startDate")));
        int optionIndexStart = rnd.nextInt(startDate.getOptions().size() - 1);
        startDate.selectByIndex(optionIndexStart++);
        
        
        //randomly select a highschool grad year
        Select gradYear = new Select(driver.findElement(By.name("gradYear")));
        int optionIndexYear = rnd.nextInt(gradYear.getOptions().size() - 1);
        gradYear.selectByIndex(optionIndexYear++);
        
        //randomly select highest level of education
        Select educationLevel = new Select(driver.findElement(By.name("educationLevel")));
        int optionIndexLevel = rnd.nextInt(educationLevel.getOptions().size() - 1);
        educationLevel.selectByIndex(optionIndexLevel++);
		        
        //randomly select between international credits
        List<WebElement> internationalCredit = driver.findElements(By.name("intlCredit"));
		internationalCredit.get(rnd.nextInt(internationalCredit.size())).click();

		//Execute the function to simulate a button click
        js.executeScript("FirstBlood.Iterator.forward()");
        
		//Send street address, choose random phone #, and email
        driver.findElement(By.id("address1")).sendKeys("123 Main");
      
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
        driver.findElement(By.id("businessPhone")).sendKeys(phoneToString);

        email = "kevin"+storyID+"-"+releaseDate+"-"+serverInstance+"-NursesNow@education180.com";
        driver.findElement(By.id("emailAddress")).sendKeys(email);
        
        
        
	}

}
