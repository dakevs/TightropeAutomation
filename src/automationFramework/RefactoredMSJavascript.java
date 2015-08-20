package automationFramework;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class RefactoredMSJavascript {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	
public static void main(String[] args) throws IOException {

		// create the Chrome webdriver
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\kevin.zagala\\workspace\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
		String testURL = "http://download.recipehub.com/info/recipehub/lb/free_recipes/index_lp5.php?partner=";
		System.out.println("This is the test URL: " + testURL);
		System.out.println();
		driver.get(testURL);
		
		// set the location of the file and create a read buffer
		String partnerCodePath = "C:\\codes.csv";
		System.out.println("This is the partner code filepath: " + partnerCodePath);
		System.out.println(" ");
		FileReader r = new FileReader(partnerCodePath);
		BufferedReader bfr = new BufferedReader(r);
		String line = null;
				
	
		// checking for meta tag keywords
		try {
			System.out.println("These are the meta tag keywords found within the page source:");
			WebElement we = driver.findElement(By
					.xpath("//meta[@name='keywords']"));
			String metaTags = we.getAttribute("content");
			System.out.println(metaTags);
			System.out.println();
			} catch (Exception e) {
				System.out.println("Meta tag keywords are Absent");
				System.out.println();
			}
				
		System.out.println("Checking for BFG pixel by Javascript ID...");
		// checking for BFG pixel
		try {
			driver.findElement(By.xpath("//script[@id='bfg']"));
			System.out.println("BFG pixel exists");
			System.out.println();
			} catch (Exception e) {
				System.out.println("BFG pixel is Absent");
				System.out.println();
			}

		System.out.println("Checking for Crazyegg pixel by Javascript ID...");
		// checking for Crazyegg pixel
		try {
			driver.findElement(By.xpath("//script[@id='crazyegg']"));
			System.out.println("Crazyegg pixel exists");
			System.out.println();
			} catch (Exception e) {
				System.out.println("Crazy pixel is Absent");
				System.out.println();
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
	
		//this is the loop that reads from the CSV file, which will stop when there's no more values to test. 
		//the only limitation here is that we don't test for when there's more than 2 columns for a partner code (i.e., the partner code also includes
		//mac-safari	mac-IE/FF	mac-chrome

		try {
			while ((line = bfr.readLine()) != null) {
			if (StringUtils.contains(line, ",,,"))
				continue;
			// System.out.println(line); //for debugging
			String[] chunks = line.split(",");
			MindsparkPartnerCode record = new MindsparkPartnerCode();
				record.setIEFFCode(chunks[0]);
				records.add(record);
		//	 System.out.println(chunks); //for debugging
				System.out.println();
				System.out.println("Testing this IE/FF Partner Code: "
					+ record.getIEFFCode());
				driver.get(testURL + record.getIEFFCode());
				
			//Create javascript executor object
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//Create mindsparkobject, execute the mindspark javascript code, and store that object in a variable
			Object mindsparkObjectURL = (Object) js.executeScript("return productObj.mainUrl;");
			//Output the mindspark object URL
			System.out.println(mindsparkObjectURL); //output object attributes
			}
			} finally {
				bfr.close();
					// System.out.println(records); //for debugging
				}
				
		System.out.println(" ");
		System.out.println("Don't forget to finish your manual checks!");
		//driver.quit();
		
	}
}
