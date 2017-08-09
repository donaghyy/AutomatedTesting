package LoginTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

import Reports.ScreenShot;
import SpreadSheets.SpreadSheetReader;
import junit.extensions.TestSetup;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;



public class CreateAccount_Login {

    private WebDriver webDriver;
    LoginPage l = new LoginPage();
    Wait<WebDriver> wait;
    
    private static ExtentReports report;
    private ExtentTest test;
    private static String reportFilePath = "Report.html";
    ScreenShot snap = new ScreenShot();
    
    //ExtentTest login = report.createTest("LOGIN TESTS");

    @BeforeClass
    public static void bc() {
    		report = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        
        		//	Name you report here
        extentHtmlReporter.config().setReportName("Test Report 1");
        extentHtmlReporter.config().setDocumentTitle("Document Title 1");
        report.attachReporter(extentHtmlReporter);
    }
    
    @Before
    public void setUp() {
        System.out.println("Before");
        System.setProperty("webdriver.chrome.driver", "/Users/daviddonaghy/Desktop/Documents/QA/QA Slides"
        		+ "/Week 5 Testing/chromedriver");
        webDriver = new ChromeDriver();
          
        l = PageFactory.initElements(webDriver, LoginPage.class);
        
        wait = new FluentWait<WebDriver>(webDriver)
				.withTimeout(30, SECONDS)
				.pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class);
        
        //test = report.createTest("TestName"); // Do this is each test so named right
    }

    @After
    public void aTest() {
        System.out.println("Test Complete.");
        webDriver.quit();
        
        //tests.clear();
        
    }
    
    @AfterClass
    public static void AfterClass() {
    		report.flush();
    }

    
    @Ignore
    public void CreateAcc_Login() throws InterruptedException, IOException {
    	test = report.createTest("Create User / Login");
    
    	// MAVEN BUILD ERROR STOPS READING FROM EXCELL FILE
    	/*SpreadSheetReader ss = new SpreadSheetReader(System.getProperty("user.dir") + "/Workbook1.xlsx");
    	 List<String> row = ss.readRow(1, "theSheet");*/
    	 
    	 
    	
    		test.log(Status.WARNING, "This is a warning! Used to report warnings");
    		String imagePath = ScreenShot.take(webDriver, "TEST IMAGE");
    	
    		System.out.println("Begin Test ...");
        webDriver.navigate().to("http://thedemosite.co.uk/login.php");
        
        l.goRegistration();
        
        l.enterCredentials("test", "test");
        //l.enterCredentials(row.get(0).toString(), row.get(0).toString());
        		
        l.submitClick();
        
        l.goLogin();
        		
        l.enterCredentials("test", "test");
        
        		//snap.take(webDriver, "TEST IMAGE"); // takes screenshot and saves to computer
        		test.addScreenCaptureFromPath(snap.take(webDriver, "Credentials")); // takes screenshot for report
        
        l.submitClick();
        
        
        
        assertEquals("**Successful Login**", wait.until(webDriver -> l.getStatus()));
        
        test.log(Status.INFO,"Info level");
        
        TimeUnit.SECONDS.sleep(2);
        webDriver.quit();
        
        
        
    }
    
    @Ignore
    public void drag() throws InterruptedException{
    	test = report.createTest("Dragable");    
    	
    	webDriver.navigate().to("http://demoqa.com/");
    	webDriver.manage().window().maximize();
    	
    		webDriver.findElement(By.id("menu-item-140")).click();
    		TimeUnit.SECONDS.sleep(2);
    		
    		Actions a = new Actions(webDriver);
    		WebElement drag = webDriver.findElement(By.id("draggable"));
    		a.moveToElement(drag).clickAndHold().moveByOffset(250, 250).release().build().perform();
    		
    		TimeUnit.SECONDS.sleep(5);
    }
    
    @Ignore
    public void dragAndDrop() throws InterruptedException{
    	test = report.createTest("Dropable");    
    	
    	webDriver.navigate().to("http://demoqa.com/");
    	webDriver.manage().window().maximize();
    	
    		webDriver.findElement(By.id("menu-item-141")).click();
    		TimeUnit.SECONDS.sleep(2);
    		
    		Actions a = new Actions(webDriver);
    		WebElement drag = webDriver.findElement(By.id("draggableview"));
    		WebElement drop = webDriver.findElement(By.id("droppableview"));
    		
    		a.moveToElement(drag).clickAndHold().moveToElement(drop).release().build().perform();
    		
    		
    		TimeUnit.SECONDS.sleep(5);
    }
    
    @Ignore
    public void resize() throws InterruptedException{
    	test = report.createTest("Resize");    
    	
    	webDriver.navigate().to("http://demoqa.com/");
    	webDriver.manage().window().maximize();
    	
    		webDriver.findElement(By.id("menu-item-143")).click();
    		TimeUnit.SECONDS.sleep(2);
    		
    		Actions a = new Actions(webDriver);
    		WebElement resize = webDriver.findElement(By.cssSelector
    				("div.ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se"));
    				
    		
    		a.moveToElement(resize).clickAndHold().moveByOffset(399, 200).release().build().perform();
    		
    		
    		TimeUnit.SECONDS.sleep(5);
    }
    

    @Test
    public void sortTiles() throws InterruptedException{
    	test = report.createTest("Sort Tiles");    
    	Actions a = new Actions(webDriver);
    	
    	webDriver.navigate().to("http://demoqa.com/");
    	webDriver.manage().window().maximize();
    	
    	webDriver.findElement(By.id("menu-item-151")).click();
    	webDriver.findElement(By.cssSelector("#ui-id-3")).click();
    		
		WebElement first = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(1)"));
    		WebElement sec = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(2)"));
    		WebElement third = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(3)"));
    		WebElement fourth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(4)"));
    		WebElement fith = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(5)"));
    		WebElement sixth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(6)"));
    		WebElement seventh = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(7)"));
    		WebElement eighth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(8)"));
    		WebElement nineth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(9)"));
    		WebElement tenth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(10)"));
    		WebElement eleventh = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(11)"));
    		WebElement twelth = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(12)"));

    		/*ArrayList<WebElement> sort = new ArrayList<WebElement>();
    		for(int i=12; i<=1; i--) {
    			WebElement el = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child("+i+")"));
    			sort.add(el);
    			a.moveToElement(el).clickAndHold().moveToElement(first)
        		.release().build().perform();
    		}
    				
    		String firstPos = first.getLocation().toString();
    		int xVal = Integer.parseInt(firstPos.substring(1,4));
    		int yVal = Integer.parseInt(firstPos.substring(6,9));
    		
    		for (int i=1; i== sort.size(); i++) {
    			a.moveToElement(sort.get(i)).clickAndHold().moveToElement(twelve)
        		.release().build().perform();
    		}
    		*/
    		
    		a.moveToElement(twelth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(eleventh).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(tenth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(nineth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(eighth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(seventh).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(sixth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(fith).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(fourth).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(third).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		a.moveToElement(sec).clickAndHold().moveToElement(first)
    		.release().build().perform();
    		
    		
    		TimeUnit.SECONDS.sleep(10);
    }
    
    
    
    
    
    
    
    
    
    

}