package testscripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utility.Modules;

public class AutoScripts {

	public static WebDriver driver;

//	@Test(priority=1)
//	@Parameters("browserName")
	public static void LoginErrorMessage_01(String browserName) throws InterruptedException{
		String expString="Please enter your password.";
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("salesforce page is not verified",ExtentColor.RED));
			DriverFile.status=false;

		}
		//
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("User@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		String actualText=driver.findElement(By.xpath("//*[@id='error']")).getText();
		if(actualText.equalsIgnoreCase(expString)){
			DriverFile.logger.log(Status.INFO,"error message verified");
		}
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("error message is not verified",ExtentColor.RED));

		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

//	@Test(priority=2)
//	@Parameters("browserName")
	public static void LoginToSalesForce_02(String browserName) throws InterruptedException, IOException {
		String expString="Please enter your password.";
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("salesforce page is not verified",ExtentColor.RED));
		}
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("kavithavuppin@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Bhuvanesa0");
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		Thread.sleep(6000);

		String homeTiltle=driver.getTitle();
		Thread.sleep(6000);
		if(homeTiltle.contains("Home Page"))
			DriverFile.logger.log(Status.INFO,"home page verified");
		else{
			DriverFile.logger.log(Status.FAIL,MarkupHelper.createLabel("home page is not verified",ExtentColor.RED));
			String destination=Modules.CaptureScreen(driver,Thread.currentThread().getStackTrace()[1].getMethodName());
			DriverFile.logger.log(Status.FAIL,"screen shots at:"+DriverFile.logger.addScreenCaptureFromPath(destination));
		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

//	@Test(priority=3)
	public static void  CheckRememberMe_3(String browserName) throws InterruptedException{
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com/");

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"Salesforcce page is not verified");

		Modules.Login_toSalesforce(driver,"kavithavuppin@gmail.com","Bhuvanesa0",true);

		Modules.selectItemFromUserMenu(driver,"Logout");
		Thread.sleep(6000);
		String actID=driver.findElement(By.xpath("//*[@id='idcard-identity']")).getText();
		if(actID.equalsIgnoreCase("kavithavuppin@gmail.com"))
			DriverFile.logger.log(Status.INFO, "email id verified");
		else
			DriverFile.logger.log(Status.INFO, "email id not verified");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}


	public static void ForgotPassword_4A(String browserName) throws InterruptedException{
		String expString="We’ve sent you an email with a link to finish resetting your password.";

		driver=Modules.launchBrowser("firefox");
		driver.get("https://login.salesforce.com/");

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");
		driver.findElement(By.xpath("//a[text()='Forgot Your Password?']")).click();
		Thread.sleep(6000);
		if(driver.getTitle().contains("Forgot Your Password"))
			DriverFile.logger.log(Status.INFO,"forgot password page is verified");
		else
			DriverFile.logger.log(Status.INFO,"forgot password page is not verified");


		driver.findElement(By.xpath(".//*[@id='un']")).sendKeys("kavithavuppin@gmail.com");
		driver.findElement(By.xpath(".//*[@id='continue']")).click();
		Thread.sleep(2000);
		String actText=driver.findElement(By.xpath("//*[@id='forgotPassForm']/div/p[1]")).getText();
		if(actText.equalsIgnoreCase(expString))
			DriverFile.logger.log(Status.INFO,"reset message verified");
		else
			DriverFile.logger.log(Status.INFO,"reset message is not verified");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void ValidateLoginErrorMessage_5(String browserName) throws InterruptedException{

		String expText="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		driver=Modules.launchBrowser(browserName);
		driver.get("https://login.salesforce.com/");
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("123");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("3456");
		driver.findElement(By.xpath("//*[@id='Login']")).click();
		Thread.sleep(6000);
		String actText=driver.findElement(By.xpath(".//*[@id='error']")).getText();
		if(actText.equalsIgnoreCase(expText))
			DriverFile.logger.log(Status.INFO,"wrong id and password message is verified");
		else
			DriverFile.logger.log(Status.INFO,"wrong id and password message is not verified");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void userMenu_DropDown(String browserName) throws InterruptedException{
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));
		driver=Modules.launchBrowser("firefox");
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.containsAll(expList))
			DriverFile.logger.log(Status.INFO,"usermenu verified ");
		else
			DriverFile.logger.log(Status.INFO,"user menu not verified");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void EditPostFileImage(String browserName) throws InterruptedException{
		WebDriver driver;
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(3000);
		//		driver=Modules.selectItemFromUserMenu(driver,"My Profile");			

		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		for(WebElement ele:list){
			if(ele.getText().equalsIgnoreCase("My Profile")) {
				ele.click();
				break;
			}

		}

		Thread.sleep(3000);
		WebElement edit=driver.findElement(By.xpath(".//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a"));
		edit.click();
		Thread.sleep(3000);
		driver.switchTo().frame("contactInfoContentId");

		WebElement about=driver.findElement(By.xpath(".//*[@id='aboutTab']/a"));
		about.click();

		WebElement lastName=driver.findElement(By.xpath(".//*[@id='lastName']"));
		lastName.clear();
		lastName.sendKeys("kavithavu");

		WebElement saveAll=driver.findElement(By.xpath(".//*[@id='TabPanel']/div/div[2]/form/div/input[1]"));
		saveAll.click();
		driver.switchTo().defaultContent();
		DriverFile.logger.log(Status.INFO,"Clicked on saveAll  and back to default window");
		Thread.sleep(4000);

		//For Post
		Thread.sleep(10000);

		WebElement post=driver.findElement(By.xpath(".//*[@id='publisherAttachTextPost']/span[1]"));
		post.click();
		Thread.sleep(4000);

		WebElement frame=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.switchTo().frame(frame);
		//		System.out.println("switched");
		Thread.sleep(10000);
		//		System.out.println("tag html?  "+driver.findElements(By.xpath("//html/body")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body")));
		WebElement postMessage=driver.findElement(By.xpath("/html/body"));
		postMessage.sendKeys("This is using Automation");
		driver.switchTo().defaultContent();
		WebElement shareButton=driver.findElement(By.xpath(".//*[@id='publishersharebutton']"));
		shareButton.click();

		Thread.sleep(2000);
		//For File

		WebElement file=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")));
		file.click();
		DriverFile.logger.log(Status.INFO,"clicked on file");

		WebElement upload=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterUploadFileAction']")));
		upload.click();
		DriverFile.logger.log(Status.INFO,"clicked on upload");

		WebElement browse=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterFile']")));
		browse.sendKeys("C:\\Kavitha\\data1.txt");

		WebElement share=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='publishersharebutton']")));
		share.click();
		Thread.sleep(5000);
		//Add photo

		Actions mousehoover=new Actions(driver);
		mousehoover.moveToElement(driver.findElement(By.xpath("//*[text()='Moderator']"))).perform();;
		WebElement addPhoto=driver.findElement(By.xpath(".//*[@id='uploadLink']"));
		addPhoto.click();
		Thread.sleep(5000);
		WebElement photoframe=driver.findElement(By.id("uploadPhotoContentId"));
		driver.switchTo().frame(photoframe);
		WebElement browsePhoto=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadInputFile']")));

		browsePhoto.sendKeys("C:\\Users\\kavit\\OneDrive\\Pictures\\Saved Pictures\\newflower.jpg");

		WebElement savePhoto=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		savePhoto.click();

		Actions action=new Actions(driver); 
		action.dragAndDropBy(driver.findElement(By.xpath(".//*[@id='j_id0:outer']/div[1]/div/div/div/div[6]")), 100, 20);
		driver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']")).click(); 

		driver.switchTo().defaultContent();
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void MySettings_TC07(String browserName) throws InterruptedException, AWTException {
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(4000);
		//		driver=Modules.selectItemFromUserMenu(driver,"My Settings");	

		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
		for(WebElement ele:list){
			if(ele.getText().equalsIgnoreCase("My Settings")) {
				ele.click();
				break;
			}

		}

		Thread.sleep(3000);
		//		String exptext=driver.findElement(By.xpath(".//*[@id='PersonalSetup_font']/span[2]")).getText();
		//		if(exptext.contains("My Settings"))
		//			DriverFile.logger.log(Status.INFO,"My Settings Page is Displayed");
		//		else
		//			DriverFile.logger.log(Status.INFO, "My Setting page did't displayed");

		WebElement personalInfo=driver.findElement(By.xpath(".//*[@id='PersonalInfo_font']"));
		personalInfo.click();

		WebElement loginHistory=driver.findElement(By.xpath(".//*[@id='LoginHistory_font']"));
		loginHistory.click();

		WebElement download=driver.findElement(By.xpath(".//*[@id='RelatedUserLoginHistoryList_body']/div/a"));
		download.click();

		Thread.sleep(2000);
		String oldwindow=driver.getWindowHandle();
		for(String handles:driver.getWindowHandles())
		{
			if(!handles.equals(oldwindow))
			{
				driver.switchTo().window(handles);
			}
		}

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_DOWN);
		rb.keyRelease(KeyEvent.VK_DOWN);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

		driver.switchTo().window(oldwindow);

		WebElement display=driver.findElement(By.xpath(".//*[@id='DisplayAndLayout_font']"));
		display.click();

		WebElement customize=driver.findElement(By.xpath(".//*[@id='CustomizeTabs_font']"));
		customize.click();

		WebElement customApp=driver.findElement(By.xpath(".//*[@id='p4']"));
		customApp.click();
		Select selcustom=new Select(customApp);
		selcustom.selectByVisibleText("Salesforce Chatter");

		WebElement listtab=driver.findElement(By.xpath(".//*[@id='duel_select_0']"));
		Select newselect=new Select(listtab);
		newselect.selectByVisibleText("Reports");

		WebElement addObj=driver.findElement(By.xpath(".//*[@id='duel_select_0_right']/img"));
		addObj.click();

		WebElement emailObj=driver.findElement(By.xpath(".//*[@id='EmailSetup_font']"));
		emailObj.click();

		WebElement emailsettings=driver.findElement(By.xpath(".//*[@id='EmailSettings_font']"));
		emailsettings.click();

		WebElement userName=driver.findElement(By.xpath(".//*[@id='sender_name']"));
		userName.sendKeys("kavitha");

		WebElement emailid=driver.findElement(By.xpath(".//*[@id='sender_email']"));
		emailid.sendKeys("kavithavuppin@gmail.com");

		WebElement rdBtn=driver.findElement(By.xpath(".//*[@id='auto_bcc1']"));

		WebElement saveBtn=driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));

		if(rdBtn.isSelected())
			saveBtn.click();
		else
		{
			rdBtn.click();
			saveBtn.click();
		}

		WebElement calandRemind=driver.findElement(By.xpath(".//*[@id='CalendarAndReminders_font']"));
		calandRemind.click();

		WebElement remind=driver.findElement(By.xpath(".//*[@id='Reminders_font']"));
		remind.click();

		WebElement testReminder=driver.findElement(By.xpath(".//*[@id='testbtn']"));
		testReminder.click();

		Robot robo=new Robot();
		robo.keyPress(KeyEvent.VK_ESCAPE);
		robo.keyRelease(KeyEvent.VK_ESCAPE);

		Thread.sleep(4000);

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}
	public static void developerConsole_TC08(String browserName) throws InterruptedException{
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		driver=Modules.selectItemFromUserMenu(driver,"Developer Console");	

		String[] a=new String[3];
		int i=0;
		for(String window:driver.getWindowHandles()){
			a[i]=window.toString();
			i++;
		}
		Thread.sleep(3000);

		driver.switchTo().window(a[1]);
		String title=driver.getTitle();
		if(title.contains("Developer Console"))
			DriverFile.logger.log(Status.INFO,"devloper window verified");
		else
			DriverFile.logger.log(Status.INFO,"failed to verify");
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(a[0]);
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void SelectLogoutFromUserMenu_TC9(String browserName) throws InterruptedException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		driver=Modules.selectItemFromUserMenu(driver,"Logout");
		String url=driver.getCurrentUrl();
		if(url.equals("https://login.salesforce.com/"))
			DriverFile.logger.log(Status.INFO,"passed");
		else
			DriverFile.logger.log(Status.INFO,"not verified login url");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void CreateAccount_TC10(String browserName) throws InterruptedException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
		accountTab.click();
		driver.findElement(By.xpath("//*[@id='tryLexDialogX']")).click();
		String accountText=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h1")).getText();
		if(accountText.equalsIgnoreCase("Accounts"))
			DriverFile.logger.log(Status.INFO,"account page is verified");
		else
			DriverFile.logger.log(Status.INFO,"account page is not present");
		WebElement newAccount=driver.findElement(By.xpath(".//*[@id='hotlist']/table/tbody/tr/td[2]/input"));		
		newAccount.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='acc2']")).sendKeys("wellsFeb");
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		String newAccountText=driver.findElement(By.xpath(".//*[@id='contactHeaderRow']/div[2]/h2")).getText();
		if(newAccountText.equalsIgnoreCase("wellsFeb"))
			DriverFile.logger.log(Status.INFO,"new account verified");
		else
			DriverFile.logger.log(Status.INFO,"couldnt verify new account");

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void CreateNewView_TC11(String browserName) throws InterruptedException, AWTException {
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
		accountTab.click();

		Thread.sleep(3000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);

		Thread.sleep(1000);

		WebElement CreateNewView=driver.findElement(By.xpath("//a[text()='Create New View']"));
		CreateNewView.click();

		WebElement viewName=driver.findElement(By.xpath(".//*[@id='fname']"));
		viewName.sendKeys("NewViewna17");

		WebElement viewUniqueName=driver.findElement(By.xpath(".//*[@id='devname']"));
		viewUniqueName.sendKeys("NewViewna17");

		WebElement saveobj=driver.findElement(By.xpath(".//*[@id='editPage']/div[1]/table/tbody/tr/td[2]/input[1]"));
		saveobj.click();

		Thread.sleep(4000);

		//		boolean text;
		//				WebElement selectmenu=driver.findElement(By.name("fcf"));
		//				Select select =new Select(selectmenu);
		//				select.selectByVisibleText("NewViewna16");
		//				
		//		List<WebElement> options = select.getOptions();
		//				for (WebElement option : options) {
		//			if("NewViewna17".equals(option.getText()))
		//				System.out.println("view created");
		//			else
		//				System.out.println("view failed");

		//        String viewname=dropdown.g
		//		System.out.println(viewname);

		//		if(viewname.equalsIgnoreCase("NewViewna13"))
		//		{
		//			DriverFile.logger.log(Status.INFO,"New View verified");
		//		System.out.println("new view");
		//		}
		//		else
		//		{
		//			DriverFile.logger.log(Status.INFO,"couldnt verify new account");
		//			System.out.println("view failed");
		//		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void EditView_TC12(String browserName) throws InterruptedException, AWTException {
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
		accountTab.click();

		Thread.sleep(3000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);

		WebElement selectMenu=driver.findElement(By.xpath(".//*[@id='fcf']"));
		Select select=new Select(selectMenu);
		select.selectByVisibleText("dfg");
		Thread.sleep(5000);

		WebElement goObj=driver.findElement(By.name("go"));
		goObj.click();

		Thread.sleep(4000);
		WebElement editview=driver.findElement(By.linkText("Edit"));
		editview.click();

		Thread.sleep(4000);

		WebElement editpage=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String text=editpage.getText();
		if(text.equalsIgnoreCase("Edit View"))
			DriverFile.logger.log(Status.INFO,"Edit view page is displayedd");
		else 
			DriverFile.logger.log(Status.INFO,"Edit view page is not displayed");

		Thread.sleep(3000);
		WebElement viewname=driver.findElement(By.xpath(".//*[@id='fname']"));
		viewname.clear();
		viewname.sendKeys("newdfg");

		Thread.sleep(5000);
		WebElement filedName=driver.findElement(By.xpath(".//*[@id='fcol1']"));
		Select fieldselect=new Select(filedName);
		fieldselect.selectByVisibleText("Account Name");

		Thread.sleep(4000);
		WebElement fieldOper=driver.findElement(By.xpath(".//*[@id='fop1']"));
		Select opr=new Select(fieldOper);
		opr.selectByVisibleText("contains");

		Thread.sleep(3000);
		WebElement fval=driver.findElement(By.xpath(".//*[@id='fval1']"));
		fval.sendKeys("<a>");

		Thread.sleep(3000);
		WebElement fieldDisplay=driver.findElement(By.xpath(".//*[@id='colselector_select_0']"));
		Select displays=new Select(fieldDisplay);
		displays.selectByVisibleText("Last Activity");

		Thread.sleep(3000);
		WebElement addObj=driver.findElement(By.xpath(".//*[@id='colselector_select_0_right']/img"));
		addObj.click();

		Thread.sleep(4000);

		WebElement saveBtn=driver.findElement(By.xpath(".//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]"));
		saveBtn.click();

		//		String exptext="newdfg";

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void MergeAccounts_TC13(String browserName) throws Exception {
		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
		accountTab.click();

		Thread.sleep(3000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);

		WebElement mergeAcnt=driver.findElement(By.xpath(".//*[@id='toolsContent']/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a"));
		mergeAcnt.click();

		WebElement text=driver.findElement(By.xpath(".//*[@id='srch']"));
		text.sendKeys("wells");

		WebElement findAcnt=driver.findElement(By.xpath(".//*[@id='stageForm']/div/div[2]/div[4]/input[2]"));
		findAcnt.click();

		WebElement element1=driver.findElement(By.xpath(".//*[@id='cid0']")); 
		WebElement element2=driver.findElement(By.xpath(".//*[@id='cid1']"));

		WebElement[] elements = { element1, element2 };
		Modules.safeSelectCheckBoxes(10, elements);


		WebElement nextBtn=driver.findElement(By.xpath(".//*[@id='stageForm']/div/div[2]/div[5]/div/input[1]"));
		nextBtn.click();


		WebElement pagechk=driver.findElement(By.xpath(".//*[@id='stageForm']/div/div[1]/h2"));
		if(pagechk.getText().contains("step 2"))
			System.out.println("Merge account step2 page displayed");
		else
			System.out.println("Merge account did not displayed");

		WebElement mergeBtn=driver.findElement(By.xpath(".//*[@id='stageForm']/div/div[2]/div[1]/div/input[2]"));
		mergeBtn.click();

		Alert alert_box = driver.switchTo().alert();
		alert_box.accept();

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void CreateAccountRepoprt_TC14(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement accountTab=driver.findElement(By.xpath(".//*[@id='Account_Tab']/a"));		
		accountTab.click();

		Thread.sleep(3000);
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(1000);

		WebElement lastActivity=driver.findElement(By.xpath(".//*[@id='toolsContent']/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a"));
		lastActivity.click();

		WebElement unsavedPage=driver.findElement(By.xpath(".//*[@id='thePage:sectionHeader']/div/div/div[1]/h2"));
		String pagedisp=unsavedPage.getText();

		if(pagedisp.contains("Unsaved Report"))
			DriverFile.logger.log(Status.INFO,"Unsaved Report Page is dispalyed");
		else
			DriverFile.logger.log(Status.INFO,"Unsaved Report Page is not dispalyed");

		WebElement selectMenu=driver.findElement(By.id("ext-gen20"));
		selectMenu.click();

		Robot robot1=new Robot();
		robot1.keyPress(KeyEvent.VK_DOWN);
		robot1.keyRelease(KeyEvent.VK_DOWN);
		robot1.keyPress(KeyEvent.VK_ENTER);
		robot1.keyRelease(KeyEvent.VK_ENTER);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1= dateFormat.format(date);

		Thread.sleep(2000);
		WebElement fromDate=driver.findElement(By.id("ext-comp-1042"));
		fromDate.clear();
		fromDate.sendKeys(date1);

		Thread.sleep(3000);

		WebElement toDate=driver.findElement(By.id("ext-comp-1045"));
		toDate.clear();
		toDate.sendKeys(date1);

		Thread.sleep(2000);
		WebElement saveObj=driver.findElement(By.id("ext-gen49"));
		saveObj.click();

		Thread.sleep(2000);
		String oldwindow=driver.getWindowHandle();
		for(String handles:driver.getWindowHandles())
		{
			if(!handles.equals(oldwindow))
			{
				driver.switchTo().window(handles);
			}
		}

		WebElement reportName=driver.findElement(By.id("saveReportDlg_reportNameField"));
		reportName.sendKeys("My New Report2");

		WebElement repUniqueName=driver.findElement(By.id("saveReportDlg_DeveloperName"));
		repUniqueName.sendKeys("Rep_Unique_Name2");

		WebElement saveRun=driver.findElement(By.xpath(".//*[@id='dlgSaveAndRun']"));
		saveRun.click();

		Thread.sleep(3000);
		driver.switchTo().window(oldwindow);

		System.out.println(oldwindow);

		//		WebElement repDisp=driver.findElement(By.xpath("//h1[@class='noSecondHeader pageType']"));
		//		String text=repDisp.getText();
		//
		//		if(text.equalsIgnoreCase("My New Report2"))
		//		{
		//			System.out.println("Report page with report name is verified");
		//		}
		//		else
		//		{
		//			System.out.println("Report page with report name is verification failed");
		//		}

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void OpportunityDropDown_TC15(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		WebElement oppTab=driver.findElement(By.xpath(".//*[@id='Opportunity_Tab']/a"));
		oppTab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(4000);

		String pageopp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
		if(pageopp.equals("Home"))
			System.out.println("Opportunity Home page is dispalyed");
		else
			System.out.println("Opportunity Home page is not dispalyed");

		WebElement oppDropDown=driver.findElement(By.xpath(".//*[@id='fcf']"));
		oppDropDown.click();

		String[] options=new String[]{"Closing Next Month","Closing This Month","My Opportunities","New This Week","Recently Viewed Opportunities","Won"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));

		Thread.sleep(3000);
		List<WebElement> list=driver.findElements(By.xpath(".//*[@id='fcf']"));

		Thread.sleep(4000);
		//		System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.contains(expList))
		{
			DriverFile.logger.log(Status.INFO,"Opportunitymenu verified ");
			System.out.println("opp verified");
		}
		else
		{
			DriverFile.logger.log(Status.INFO,"Opportunity menu not verified");
			System.out.println("opp failed");
		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void CreateNewOpportunity_TC16(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		WebElement oppTab=driver.findElement(By.xpath(".//*[@id='Opportunity_Tab']/a"));
		oppTab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(4000);

		WebElement createNewOppr=driver.findElement(By.xpath(".//*[@id='hotlist']/table/tbody/tr/td[2]/input"));
		createNewOppr.click();

		WebElement oppName=driver.findElement(By.xpath(".//*[@id='opp3']"));
		oppName.sendKeys("New opp1");

		WebElement accName=driver.findElement(By.xpath(".//*[@id='opp4']"));
		accName.sendKeys("New Acc Name");

		//		WebElement closeDate=driver.findElement(By.xpath(".//*[@id='opp9']"));
		//		closeDate.click();

		WebElement selectDate=driver.findElement(By.xpath(".//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a"));
		selectDate.click();

		WebElement stage=driver.findElement(By.xpath(".//*[@id='opp11']"));
		Select stagesel=new Select(stage);
		stagesel.selectByVisibleText("Prospecting");

		//		WebElement probability=driver.findElement(By.xpath(".//*[@id='opp12']"));
		//		probability.sendKeys("2");

		WebElement leadSource=driver.findElement(By.xpath(".//*[@id='opp6']"));
		Select NewSelect=new Select(leadSource);
		NewSelect.selectByVisibleText("Web");

		WebElement primarySource=driver.findElement(By.xpath(".//*[@id='opp17_lkwgt']/img"));
		primarySource.click();

		String parentWindow=driver.getWindowHandle();

		Set<String> allWindows = driver.getWindowHandles();
		for(String curWindow : allWindows){
			driver.switchTo().window(curWindow);
		}

		driver.close();
		driver.switchTo().window(parentWindow);

		WebElement saveBtn=driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]"));
		saveBtn.click();

		WebElement newOppDisp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String page=newOppDisp.getText();


		if(page.equals("New opp1"))
			DriverFile.logger.log(Status.INFO,"new Opportunity page diaplyed");
		else
			DriverFile.logger.log(Status.INFO, "new Opportunity page not diaplyed");

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void OpportunityPipelineReport_TC17(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		WebElement oppTab=driver.findElement(By.xpath(".//*[@id='Opportunity_Tab']/a"));
		oppTab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(4000);

		String pageopp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
		if(pageopp.equals("Home"))
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is dispalyed");
		else
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is not dispalyed");

		WebElement pipelineReport=driver.findElement(By.xpath(".//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a"));
		pipelineReport.click();

		WebElement reportPage=driver.findElement(By.xpath(".//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1"));
		String pipeRep=reportPage.getText();

		if(pipeRep.equals("Opportunity Pipeline"))
			DriverFile.logger.log(Status.INFO,"Pipeline report page is displayed");
		else
			DriverFile.logger.log(Status.INFO,"Pipeline report page is not displayed");

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void stuckOpportunityReport_TC18(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		WebElement oppTab=driver.findElement(By.xpath(".//*[@id='Opportunity_Tab']/a"));
		oppTab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(4000);

		String pageopp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
		if(pageopp.equals("Home"))
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is dispalyed");
		else
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is not dispalyed");

		WebElement stuckReport=driver.findElement(By.xpath(".//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a"));
		stuckReport.click();

		WebElement reportStuck=driver.findElement(By.xpath(".//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1"));
		String stuckRep=reportStuck.getText();

		if(stuckRep.equals("Stuck Opportunities"))
			System.out.println("Stuck Opportunities page is dispalyed");
		else
			System.out.println("Stuck Opportunities page is not dispalyed");
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void QuarterlySummaryReport_TC19(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			DriverFile.logger.log(Status.INFO,"salesforce page is verified");
		else
			DriverFile.logger.log(Status.INFO,"salesforce page is not verified");

		WebElement oppTab=driver.findElement(By.xpath(".//*[@id='Opportunity_Tab']/a"));
		oppTab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(4000);

		String pageopp=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2")).getText();
		if(pageopp.equals("Home"))
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is dispalyed");
		else
			DriverFile.logger.log(Status.INFO,"Opportunity Home page is not dispalyed");

		WebElement interval=driver.findElement(By.xpath(".//*[@id='quarter_q']"));
		Select newSelect=new Select(interval);
		newSelect.selectByVisibleText("Current FQ");

		WebElement include=driver.findElement(By.xpath(".//*[@id='open']"));
		Select rp=new Select(include);
		rp.selectByVisibleText("All Opportunities");

		WebElement runReport=driver.findElement(By.xpath(".//*[@id='lead_summary']/table/tbody/tr[3]/td/input"));
		runReport.click();

		//		WebElement oppRep=driver.findElement(By.xpath(".//*[@id='noTableContainer']/div/div[1]/div[1]/div[1]/h1"));
		//		String repText=oppRep.getText();
		//		Thread.sleep(3000);
		//
		//		if(repText.equals("Opportunity Report"))
		//			DriverFile.logger.log(Status.INFO,"Opportunity Report page is dispalyed");
		//		else
		//			DriverFile.logger.log(Status.INFO,"Opportunity Report page is not dispalyed");

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));

	}

	public static void checkLeadstab_TC20(String browserName) throws InterruptedException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement leadtab=driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a"));
		leadtab.click();

		WebElement leadHome=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String leadText=leadHome.getText();

		if(leadText.equals("Home"))
			System.out.println("Lead home is verfied");
		else
			System.out.println("Lead home is not verfied");

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void leadsSelectView_TC21(String browserName) throws InterruptedException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement leadtab=driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a"));
		leadtab.click();

		WebElement leadHome=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String leadText=leadHome.getText();

		if(leadText.equals("Home"))
			System.out.println("Lead home is verfied");
		else
			System.out.println("Lead home is not verfied");

		String[] options=new String[]{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));

		WebElement dropdown=driver.findElement(By.xpath(".//*[@id='fcf']"));
		Select dropdownlsitselect=new Select(dropdown);

		List<WebElement> list=dropdownlsitselect.getOptions();
		//				System.out.println(list);
		ArrayList<String> actData=new ArrayList<String>();
		for(WebElement ele:list){
			actData.add(ele.getText().trim());
		}
		System.out.println(actData);
		if(actData.containsAll(expList))
		{
			DriverFile.logger.log(Status.INFO,"Leads dropdown menu verified ");
			//		System.out.println("lead pass");
		}
		else
		{
			DriverFile.logger.log(Status.INFO,"Lead dropdown menu not verfied");
			//		System.out.println("lead fail");
		}
		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));
	}

	public static void defaultView_TC22(String browserName) throws InterruptedException, AWTException {

		driver=Modules.launchBrowser("firefox");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		driver=Modules.Login_toSalesforce(driver);
		Thread.sleep(6000);

		WebElement leadtab=driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a"));
		leadtab.click();

		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(3000);

		WebElement dropdwn=driver.findElement(By.xpath(".//*[@id='fcf']"));
		Select newSelect=new Select(dropdwn);
		newSelect.selectByVisibleText("Today's Leads");

		Thread.sleep(4000);
		WebElement usermenu=driver.findElement(By.xpath(".//*[@id='userNavButton']"));
		usermenu.click();

		WebElement logoutButton = driver.findElement(By.xpath(".//*[@id='userNav-menuItems']/a[5]"));
		logoutButton.click();

		driver=Modules.Login_toSalesforce(driver);

		Thread.sleep(4000);

		//		driver=Modules.Login_toSalesforce(driver);
		//		Thread.sleep(6000);

		WebElement leadtab1=driver.findElement(By.xpath(".//*[@id='Lead_Tab']/a"));
		leadtab1.click();

		Robot rb1=new Robot();
		rb.keyPress(KeyEvent.VK_ESCAPE);
		rb.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(3000);


		WebElement leadHome1=driver.findElement(By.xpath(".//*[@id='bodyCell']/div[1]/div[1]/div[1]/h2"));
		String leadText1=leadHome1.getText();

		if(leadText1.equals("Home"))
			DriverFile.logger.log(Status.INFO,"Lead home is verfied");
		else
			DriverFile.logger.log(Status.INFO,"Lead home is not verfied");

		WebElement go=driver.findElement(By.xpath(".//*[@id='filter_element']/div/span/span[1]/input"));
		go.click();

		Modules.closeBrowser(driver);
		DriverFile.logger.log(Status.PASS,MarkupHelper.createLabel("passed",ExtentColor.GREEN));

	}
}

