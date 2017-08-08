package LoginTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;


//import static jdk.nashorn.internal.runtime.PropertyHashMap.findElement;



public class CreateAccount_Login {

    private WebDriver webDriver;
    LoginPage l = new LoginPage();

    @Before
    public void bTest() {
        System.out.println("Before");
        System.setProperty("webdriver.chrome.driver", "/Users/daviddonaghy/Desktop/Documents/QA/QA Slides"
        		+ "/Week 5 Testing/chromedriver");
        webDriver = new ChromeDriver();
          
        l = PageFactory.initElements(webDriver, LoginPage.class);
        
        
    }

    @After
    public void aTest() {
        System.out.println("After");
        webDriver.quit();
    }

    @Test
    public void CreateAcc_Login() throws InterruptedException {
      
    		System.out.println("Begin Test");
        webDriver.navigate().to("http://thedemosite.co.uk/login.php");
        
        l.goRegistration();
        		l.sleep(2);
        l.enterCredentials("test", "test");
        		
        l.submitClick();
        		l.sleep(2);
        l.goLogin();
        		
        l.enterCredentials("test", "test");
        		l.sleep(2);
        l.submitClick();
        assertEquals("**Successful Login**", l.getStatus());
        
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
        
        TimeUnit.SECONDS.sleep(5);
        webDriver.quit();
    }
    


}