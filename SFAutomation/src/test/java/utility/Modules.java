package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Modules {
	public static XSSFWorkbook wb;
	public static WebDriver driver;
	
	public static WebDriver launchBrowser(String name){
		if(name.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\Kavitha\\Selenium_AutomationWorkspace\\Jan_selenium\\src\\test\\java\\utility\\geckodriver.exe");
		driver=new FirefoxDriver();
		}
		else if(name.equalsIgnoreCase("chrome")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.chrome.driver","C:\\Kavitha\\Selenium_AutomationWorkspace\\Jan_selenium\\src\\test\\java\\utility\\chromedriver1.exe");
			driver=new ChromeDriver();
			//driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("ie")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.ie.driver","C:\\Kavitha\\Selenium_AutomationWorkspace\\Jan_selenium\\src\\test\\java\\utility\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		return driver;
	}

	public static void closeBrowser(WebDriver driver){
		try{
			driver.quit();
		}
		catch(Exception e){
			
		}
	}
	public static WebDriver Login_toSalesforce(WebDriver driver) throws InterruptedException{
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys("kavithavuppin@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Bhuvanesa0");
		Thread.sleep(5000);//due to my antivirus secure tab
		driver.findElement(By.id("Login")).click();
		Thread.sleep(6000);
		return driver;
	}
	
	public static WebDriver Login_toSalesforce(WebDriver driver,String id,String password,boolean remember) throws InterruptedException{
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.id("username")).sendKeys(id);
		driver.findElement(By.id("password")).sendKeys(password);
		if(remember)
			driver.findElement(By.xpath("//*[@id='rememberUn']")).click();
		driver.findElement(By.id("Login")).click();
		Thread.sleep(6000);
		return driver;
	}
	
	public static String[][] readXlSheet(String link,String sheetName) throws IOException{
		FileInputStream fio=new FileInputStream(new File(link));
		wb=new XSSFWorkbook(fio);
		XSSFSheet sheet=wb.getSheet(sheetName);
		int trow=sheet.getLastRowNum()+1;
		int tcol=sheet.getRow(0).getLastCellNum();
		String[][] s = new String[trow][tcol];
		System.out.println("total rows="+trow+" and total column="+tcol);
		for(int i=0;i<trow;i++){
			for(int j=0;j<tcol;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType==XSSFCell.CELL_TYPE_NUMERIC)
					s[i][j]=String.valueOf((int)sheet.getRow(i).getCell(j).getNumericCellValue());
				else
				s[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();	
		
			}
		}
		
		return s;
	}
	
	public static void writeXlSheet(String link, String sheetName,String text,int row,int col) throws IOException {
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fio);
		XSSFSheet sheet=wb.getSheet(sheetName);
		sheet.getRow(row).getCell(col).setCellValue(text);
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	
	
	public static void setXlColorStyle(String link,String sheetName,int iRow,int iCol,String status) throws IOException{
		File f=new File(link);
		FileInputStream fio=new FileInputStream(f);
		XSSFWorkbook wb=new XSSFWorkbook(fio);
		XSSFSheet sheet=wb.getSheet(sheetName);
		
		
		XSSFRow row = sheet.getRow(iRow);
		XSSFCell cell = row.getCell(iCol);
		
		if(status.equalsIgnoreCase("pass"))
			fillBackgroundColor(wb, "green", cell);
		else
			fillBackgroundColor(wb, "red", cell);
		
		FileOutputStream fop=new FileOutputStream(f);
		wb.write(fop);
		fop.flush();
		fop.close();
		}
	public static void fillBackgroundColor(XSSFWorkbook wb,String color,XSSFCell cell){
		XSSFCellStyle style=(XSSFCellStyle) wb.createCellStyle();
		if(color.equalsIgnoreCase("green")){
//			style.setFillForegroundColor(new XSSFCell.GREEN().getIndex());
			}
		else if(color.equalsIgnoreCase("red")){
//			style.setFillForegroundColor(new XSSFColor.RED().getIndex());
			}
//		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(style);
	}
	
	
	public static WebDriver selectItemFromUserMenu(WebDriver driver,String name){
		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		for(WebElement ele:list){
			if(ele.getText().equalsIgnoreCase(name))
				ele.click();
		}
		return driver;
	}
	public static String CaptureScreen(WebDriver driver,String name) throws IOException{
//		File src= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest="./Screenshots/"+name+".png";
//		FileUtils.copyFile(src, new File(dest));
		return dest;
	}

	public static void safeSelectCheckBoxes(int waitTime, WebElement... elements) throws Exception {
		WebElement checkElement = null;
		try {
			if (elements.length > 0) {
				for (WebElement currentElement : elements) {
					checkElement = currentElement;
					WebDriverWait wait = new WebDriverWait(driver, waitTime);
					wait.until(ExpectedConditions.elementToBeClickable(currentElement));

					WebElement checkBox = currentElement;
					if (checkBox.isSelected())
						System.out.println("CheckBox " + currentElement
								+ " is already selected");
					else
						checkBox.click();
				}
			} else {
				System.out
						.println("Expected atleast one element as argument to safeSelectCheckboxes function");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element - " + checkElement
					+ " is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + checkElement + " was not found in DOM"
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out
					.println("Unable to select checkbox " + e.getStackTrace());
		}
	}
}
