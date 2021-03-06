package Tests;
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
        System.out.println("Setting up ...");
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

    
    @Test
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
    
    
    
    
    
    
    
    
    
    
    
    

}