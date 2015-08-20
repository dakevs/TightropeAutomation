package automationFramework;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class MindsparkPartnerCodeChecks {

	/**
	 * @param args
	 * @throws IOException
	 */

	
	
	public static void main(String[] args) throws IOException {

		// create the Chrome webdriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\kevin.zagala\\workspace\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// !!! Change this before running the test!!!
		String testURL = "http://download.awesomeclassicgames.com/lp/single/crazybirds/index.php?partner=";
		System.out.println("This is the test URL: " + testURL);
		driver.get(testURL);

		// checking for meta tag keywords
		try {
			// System.out.println("These are the meta tag keywords found within the page source:");
			WebElement we = driver.findElement(By
					.xpath("//meta[@name='keywords']"));
			String metaTags = we.getAttribute("content");
			System.out.println(metaTags);
		} catch (Exception e) {
			System.out.println("Meta tag keywords are Absent");
		}

		// create the Mindspark Partner Code record object
		class MindsparkPartnerCode {
			private String ieFFCode;
			//private String chromeCode;
			public void setIEFFCode(String ieFirefoxCode) {
				this.ieFFCode = ieFirefoxCode;
			}
			public String getIEFFCode() {
				return ieFFCode;
			}

			// public void setChromeCode (String chromeCode){
			// this.chromeCode = chromeCode;}
			// public String getChromeCode (){
			// return chromeCode;
			// }
			
			@Override
			public String toString() {
				return ToStringBuilder.reflectionToString(this);
			}
		}

		// create a list/array of the partner codes found within the source file
		List<MindsparkPartnerCode> records = new ArrayList<MindsparkPartnerCode>();
		// set the location of the file and create a read buffer
		FileReader r = new FileReader("C:\\codes.csv");
		BufferedReader bfr = new BufferedReader(r);

		String line = null;
		try {
			while ((line = bfr.readLine()) != null) {
				if (StringUtils.contains(line, ",,,"))
					continue;
				// System.out.println(line); //for debugging
			String[] chunks = line.split(",");
			MindsparkPartnerCode record = new MindsparkPartnerCode();
			record.setIEFFCode(chunks[0]);
			records.add(record);
			// System.out.println(chunks); //for debugging
			System.out.println();
			System.out.println("Testing this IE/FF Partner Code: "
					+ record.getIEFFCode());
			driver.get(testURL + record.getIEFFCode());
			
			
			// now output the source URL for the mindspark javascript
				// implementation, which contains the partner code 
			// **** Will most likely have to change the xPath for each landing page ******
			String mindsparkURL = driver.findElement(
					By.xpath("/html/head/script[6]")).getAttribute(
					"redirect");		
			System.out.println("Mindspark Javascript inner HTML: ");
			System.out.println(mindsparkURL);
			//System.out.println();
			
			
			}
		} finally {
			bfr.close();
			// System.out.println(records); //for debugging
		}
		driver.quit();
	}
}
