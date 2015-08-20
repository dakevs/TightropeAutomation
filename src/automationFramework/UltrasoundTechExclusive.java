package automationFramework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class UltrasoundTechExclusive {

	 private static WebDriver driver = null;
	    
		public static void main(String[] args) throws IOException {
		
			// Create a new instance of the Firefox driver
	        driver = new FirefoxDriver();
	        driver.get("http://edu.getaheadcollege.com/facebook/ultrasound-tech-exclusive/");
			
	        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
	        //Story id, release date and server instance. Change this before each run to ensure that a unique email address is used
	        String storyID, releaseDate, email, serverInstance;
	        storyID = "95578976";
	        releaseDate = "05282015";
	        serverInstance = "server1";
	        
	        
	        //used to select randomness
	        Random rnd = new Random();

	        //Randomly select from Online, Campus, or Both
	        List<WebElement> radiosCampus = driver.findElements(By.name("site"));
	        radiosCampus.get(rnd.nextInt(radiosCampus.size())).click();
	        
	        //Randomly select from desired start date, to exclude the first option "Select One"
	        Select startDates = new Select(driver.findElement(By.name("startDate")));
	        int optionIndexDates = rnd.nextInt(startDates.getOptions().size() - 1);
	        startDates.selectByIndex(optionIndexDates++);
	        
	        //Randomly select from Age Range, to exclude the first option "Select One"
	        Select ageRange = new Select(driver.findElement(By.name("ageRange")));
	        int optionIndexAgeRange = rnd.nextInt(ageRange.getOptions().size() - 1);
	        ageRange.selectByIndex(optionIndexAgeRange++);
	        
	        //Randomly select from year of High School Graduation, to exclude the first option "Select One"
	        Select gradYear = new Select(driver.findElement(By.name("gradYear")));
	        int optionIndexGradYear = rnd.nextInt(gradYear.getOptions().size() - 1);
	        gradYear.selectByIndex(optionIndexGradYear++);
	        
	        //Randomly select from highest level of education, to exclude the first option "Select One"
	        Select educationLevel = new Select(driver.findElement(By.name("educationLevel")));
	        int optionIndexEducationLevel = rnd.nextInt(educationLevel.getOptions().size() - 1);
	        educationLevel.selectByIndex(optionIndexEducationLevel++);
	        
	        //Randomly select if Yes/No for international Credit question
	        List<WebElement> radiosInternational = driver.findElements(By.name("intlCredit"));
	        radiosInternational.get(rnd.nextInt(radiosInternational.size())).click();
	        
	        //Randomly select if Yes/No for citizenship question
	        List<WebElement> radiosCitizen = driver.findElements(By.name("citizen"));
	        radiosCitizen.get(rnd.nextInt(radiosCitizen.size())).click();
	        
	        //Randomly select from military affiliation, to exclude the first option "Select One"
	        Select military = new Select(driver.findElement(By.name("military")));
	        int optionIndexMilitary = rnd.nextInt(military.getOptions().size() - 1);
	        military.selectByIndex(optionIndexMilitary++);
	        
	      //Start filling in the test lead's information
	        driver.findElement(By.id("firstName")).sendKeys("Test");
	        driver.findElement(By.id("lastName")).sendKeys("Lead");
	        driver.findElement(By.id("address1")).sendKeys("123 Main");
	        driver.findElement(By.id("city")).sendKeys("Vallejo");
	        
	       
	        //Randomly select from state list, to exclude the first option "Select One"
	        Select states = new Select(driver.findElement(By.name("state")));
	        int optionIndexState = rnd.nextInt(states.getOptions().size() - 1);
	        states.selectByIndex(optionIndexState++);
	    
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
	        driver.findElement(By.id("postalCode")).sendKeys(zipToString);
	       
	        //email will use kevin+<story id>+<release date>-ultraSoundTech@education180.com to make it as unique as possible
		
	        email = "kevin"+storyID+"-"+releaseDate+"-"+
	        		serverInstance+"-ultraSoundTechExclusive@education180.com";
	        driver.findElement(By.id("emailAddress")).sendKeys(email);
	                
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

	        
	        
		}

	}
