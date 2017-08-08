package LoginTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

import Reports.ScreenShot;
import junit.extensions.TestSetup;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;



public class CreateAccount_Login {

    private WebDriver webDriver;
    LoginPage l = new LoginPage();
    Wait<WebDriver> wait;
    
    private ExtentReports report;
    private ExtentTest test;
    private String reportFilePath = "Report.html";
    ScreenShot snap = new ScreenShot();

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
        
        
        //______________________________________________________REPORTS
        report = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        report.attachReporter(extentHtmlReporter);
        test = report.createTest("TestName");
    }

    @After
    public void aTest() {
        System.out.println("Test Complete.");
        webDriver.quit();
        
        //tests.clear();
        report.flush();
    }

    
    @Test
    public void CreateAcc_Login() throws InterruptedException, IOException {
    		
    	
    			test.log(Status.WARNING, "This is a warning! Used to report warnings");
    		String imagePath = ScreenShot.take(webDriver, "TEST IMAGE");
    	
    		System.out.println("Begin Test ...");
        webDriver.navigate().to("http://thedemosite.co.uk/login.php");
        
        l.goRegistration();
        
        l.enterCredentials("test", "test");
        		
        l.submitClick();
        
        		test.log(Status.INFO,"Info level");
        
        l.goLogin();
        		snap.take(webDriver, "TEST IMAGE");
        		
        l.enterCredentials("test", "test");
        
        l.submitClick();
        
        
        
        assertEquals("**Successful Login**", wait.until(webDriver -> l.getStatus()));
        
        TimeUnit.SECONDS.sleep(5);
        webDriver.quit();
        
        
        /*WebElement UN = wait.until(new Function<WebDriver, WebElement>()
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.name("username"));
			}
		});*/
        
    }
    


}