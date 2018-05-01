package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testscripts.DriverFile;

public class ReUsableMethod {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	
	/* 
	 * Name of the Method: enterText
	 * Brief Description:Enter text to text boxes
	 * Arguments: obj --> Object, textval --> test value to be entered, objName --> Name of the object
	 * Created By: Automation team
	 * Creation date: Mar 09 2018
	 * Last Modified By: Abhi
	 * Last Modified Date: Mar 09 2018
	 * 
	 * This is called CMMI-V level coding
	 * 
	 * */
	
	public static void enterText(WebElement obj, String textVal, String objName){
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
//			DriverFile.logger.log(Status.INFO,"Pass: "+textVal+ " is entered in " + objName +" field.");
			System.out.println("Pass: "+textVal+ " is entered in " + objName +" field.");
		}else{
			System.out.println("Fail: " + objName +" field does not exist please check your application.");
//			DriverFile.status=false;
		}
	}
	/*Name of the Method: clickObj
	 * Brief Description: Click on Object
	 * Arguments: obj--->Object, objName---> name of object
	 * Created By : Automation team
	 * Creation date : Mar 09 2018
	 * Last Modified Date: Mar 15 2018
	 * Last Modified by: Divyashree
	 */
	public static void clickObj(WebElement obj1,String objName){
		if(obj1.isDisplayed()){
			obj1.click();
			System.out.println("Pass : " + objName +" is clicked" + objName);
		}
		else{
			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
		}
	}
	public static void selectCheckBox(WebElement obj,String objName){
		if(obj.isDisplayed())
		{
			if(obj.isSelected()){
			
			System.out.println("Pass" +objName+ "is already selected");
			}else{
				obj.click();
			}
			System.out.println("Fail" +objName+ "is not displayed,please check your application");
		}		
	}
    	public static void deSelectCheckBox(WebElement obj,String objName){
    		if(obj.isDisplayed())
    		{ 
    			if(obj.isSelected()){
    				obj.click();
    				System.out.println("Pass: "+ objName + " is  de selected.");
    				
    			}else{
    				System.out.println("Pass: "+ objName + " is already de selected.");
    			}

    		}
    		else
    		{
    			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
    		}
    	
    	}
    	public static void validateTextBoxContent(WebElement obj, String expectedText, String objName){
    		if(obj.isDisplayed())
    		{
    			String actualText = obj.getAttribute("value");
    			if(expectedText.equals(actualText)){
    				System.out.println("Pass: " + " Expected text '" + expectedText + "' is matching with actual text.");
    			}else{
    				System.out.println("Fail: "+" Expected text '" + expectedText + "' is not matching with actual text '"+ actualText+ "'.");
    			}
    			
    		}else{
    			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
    		}
    	}
    	
    	
    	
    	
    	public static WebDriver locateFrame(String frameName, WebDriver driver) {
    		WebDriverWait wt = new WebDriverWait(driver, 10);
    		wt.until(ExpectedConditions.visibilityOfElementLocated((By.className(frameName))));
    		driver.switchTo().frame(frameName);
    		return driver;
    	}
    	
    	
    	public static void displayFrames(WebDriver driver) {
    		int i=0;
    		for (WebElement e:driver.findElements(By.tagName("iframe"))) {
    			System.out.println(String.format("Frame %d=%s",i++, e.getTagName()+e.getText()));
    		}
    		
    	}
    	
    	public static ExtentReports startReport(String reportPath){
    		//System.getProperty("user.dir") +"/test-output/STMExtentReport.html"
    		htmlReporter = new ExtentHtmlReporter(reportPath);
    		extent = new ExtentReports ();
    		extent.attachReporter(htmlReporter);
    		extent.setSystemInfo("Host Name", "tekarch QA");
    		extent.setSystemInfo("Environment", "Automation Testing");
    		extent.setSystemInfo("User Name", "Divyashree");
    		
    		htmlReporter.config().setDocumentTitle("report status");
    		htmlReporter.config().setReportName("customized report");
    		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    		htmlReporter.config().setTheme(Theme.STANDARD);
    		return extent;
    	}
    	
    	public static ExtentTest createTestReport(String testName,ExtentReports extent){
    		logger = extent.createTest(testName);
    		return logger;
    	}
    	public static void endReport(ExtentReports extent){
    		extent.flush();
    	}



}
