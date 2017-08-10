package Tests;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Reports.ScreenShot;

public class demoQA {

	private WebDriver webDriver;
    LoginPage l = new LoginPage();
    Wait<WebDriver> wait;
    
    private static ExtentReports report;
    private ExtentTest test;
    private static String reportFilePath = "demoQAReport.html";
    ScreenShot snap = new ScreenShot();
    

    @BeforeClass
    public static void bc() {
    		report = new ExtentReports();
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        
        		//	Name you report here
        extentHtmlReporter.config().setReportName("Demo QA Report");
        extentHtmlReporter.config().setDocumentTitle("David Donaghy Tests");
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
    public void drag() throws InterruptedException{
    	test = report.createTest("Dragable");    
    	
    	webDriver.navigate().to("http://demoqa.com/");
    	webDriver.manage().window().maximize();
    	
    		webDriver.findElement(By.id("menu-item-140")).click();
    		TimeUnit.SECONDS.sleep(2);
    		
    		Actions a = new Actions(webDriver);
    		WebElement drag = webDriver.findElement(By.id("draggable"));
    		
    		a.moveToElement(drag).clickAndHold().moveByOffset(250, 250).release().build().perform();
    		TimeUnit.SECONDS.sleep(1);
    		a.moveToElement(drag).clickAndHold().moveByOffset(-144, -125).release().build().perform();
    		TimeUnit.SECONDS.sleep(1);
    		a.moveToElement(drag).clickAndHold().moveByOffset(-66, -77).release().build().perform();
    		TimeUnit.SECONDS.sleep(1);
    		a.moveToElement(drag).clickAndHold().moveByOffset(241, 123).release().build().perform();

    		TimeUnit.SECONDS.sleep(3);
    }
    
    @Test
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
    
    @Test
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
    		
    		for(int i=1; i<=12; i++) {
    			
    			WebElement el = webDriver.findElement(By.cssSelector("#sortable_grid > li:nth-child(12)"));
    			a.moveToElement(el).clickAndHold().moveToElement(first)
        		.release().build().perform();
    		}
    				
    		String firstPos = first.getLocation().toString();
    		int xVal = Integer.parseInt(firstPos.substring(1,4));
    		int yVal = Integer.parseInt(firstPos.substring(6,9));
    		
    		TimeUnit.SECONDS.sleep(7);
    }

}
