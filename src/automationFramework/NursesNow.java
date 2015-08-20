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


public class NursesNow {

	private static WebDriver driver = null;
	
	public static void main(String[] args) throws IOException {
	
		// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.get("http://ed.education180.com/online-degrees/nursing_connect/nurses_now.php");
        
        //Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);       
        
        //Story id, release date and server instance. Change this before each run to ensure that a unique email address is used
        String storyID, releaseDate, serverInstance, email;
        storyID = "95578976";
        releaseDate = "05282015";
        serverInstance = "server2";
        
        //used to select randomness
        Random rnd = new Random();
        
        //Randomly select yes/no to Nursing question
		List<WebElement> radioNurse = driver.findElements(By.name("career"));
		radioNurse.get(rnd.nextInt(radioNurse.size())).click();

		//send first/last name
        driver.findElement(By.id("firstName")).sendKeys("Test");
        driver.findElement(By.id("lastName")).sendKeys("Lead");
       
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
        driver.findElement(By.id("postalCode1")).sendKeys(zipToString);
      
        driver.findElement(By.id("btn1")).click();
		
        //Randomly select citizenship, military affiliation, age range
		List<WebElement> radioCitizen = driver.findElements(By.name("citizen"));
		radioCitizen.get(rnd.nextInt(radioCitizen.size())).click();
       
		Select military = new Select(driver.findElement(By.name("military")));
        int optionIndexMilitary = rnd.nextInt(military.getOptions().size() - 1);
        military.selectByIndex(optionIndexMilitary++);
        
        Select ageRange = new Select(driver.findElement(By.name("ageRange")));
        int optionIndexAge = rnd.nextInt(ageRange.getOptions().size() - 1);
        ageRange.selectByIndex(optionIndexAge++);
        driver.findElement(By.id("btn2")).click();
        
        //Randomly select start date, high school grad year, highest education level, and international credits
        Select startDate = new Select(driver.findElement(By.name("startDate")));
        int optionIndexStart = rnd.nextInt(startDate.getOptions().size() - 1);
        startDate.selectByIndex(optionIndexStart++);
        
        Select gradYear = new Select(driver.findElement(By.name("gradYear")));
        int optionIndexYear = rnd.nextInt(gradYear.getOptions().size() - 1);
        gradYear.selectByIndex(optionIndexYear++);
        
        Select educationLevel = new Select(driver.findElement(By.name("educationLevel")));
        int optionIndexLevel = rnd.nextInt(educationLevel.getOptions().size() - 1);
        educationLevel.selectByIndex(optionIndexLevel++);
		
        List<WebElement> internationalCredit = driver.findElements(By.name("intlCredit"));
		internationalCredit.get(rnd.nextInt(internationalCredit.size())).click();
        
		driver.findElement(By.id("btn3")).click();

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
