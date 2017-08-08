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

import com.google.common.base.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


//import static jdk.nashorn.internal.runtime.PropertyHashMap.findElement;



public class CreateAccount_Login {

    private WebDriver webDriver;
    LoginPage l = new LoginPage();
    Wait<WebDriver> wait;

    @Before
    public void bTest() {
        System.out.println("Before");
        System.setProperty("webdriver.chrome.driver", "/Users/daviddonaghy/Desktop/Documents/QA/QA Slides"
        		+ "/Week 5 Testing/chromedriver");
        webDriver = new ChromeDriver();
          
        l = PageFactory.initElements(webDriver, LoginPage.class);
        
        wait = new FluentWait<WebDriver>(webDriver)
				.withTimeout(30, SECONDS)
				.pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class);
        
        
    }

    @After
    public void aTest() {
        System.out.println("Test Complete.");
        webDriver.quit();
    }

    @Test
    public void CreateAcc_Login() throws InterruptedException {
      
    		System.out.println("Begin Test ...");
        webDriver.navigate().to("http://thedemosite.co.uk/login.php");
        
        l.goRegistration();
        		//l.sleep(3);
        l.enterCredentials("test", "test");
        		
        l.submitClick();
        		
        l.goLogin();
        		
        l.enterCredentials("test", "test");
        		
        l.submitClick();
        
        
        
        assertEquals("**Successful Login**", wait.until(webDriver -> l.getStatus()));
        
        TimeUnit.SECONDS.sleep(5);
        webDriver.quit();
        
        /*//webDriver.manage().window().maximize();
        
        //webDriver.findElement(By.cssSelector("small > a:nth-child(6)")).click();
        
        webDriver.findElement(By.name("username")).sendKeys("test");
        webDriver.findElement(By.name("password")).sendKeys("test");
        
        //webDriver.findElement(By.name("FormsButton2")).click();        
        
        //webDriver.findElement(By.cssSelector("small > a:nth-child(7)")).click();
        
        webDriver.findElement(By.name("username")).sendKeys("test");
        webDriver.findElement(By.name("password")).sendKeys("test");
        
       // webDriver.findElement(By.name("FormsButton2")).click();        
        
        assertEquals("**Successful Login**", webDriver.findElement(By.
        		cssSelector("body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b")).getText());
         */
        
        
    }
    


}