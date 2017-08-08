package LoginTest;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import static java.util.concurrent.TimeUnit.SECONDS;

import com.google.common.base.Function;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(name = "username")
	private WebElement usernameInput;
	
	@FindBy(name = "password")
	private WebElement passwordInput;
	
	@FindBy(name = "FormsButton2")
	private WebElement submitButton;

	@FindBy(css = "small  a:nth-child(6)")
	private WebElement addUserNav;
	
	@FindBy(css = "small  a:nth-child(7)")
	private WebElement LoginNav;
	
	@FindBy(css = "body  table  tbody  tr  td.auto-style1  big  blockquote  blockquote  font  center  b")
	private WebElement status;
	
	public void enterCredentials(String Username, String Password) {
		usernameInput.sendKeys(Username);
		passwordInput.sendKeys(Password);
	}
	
	public void submitClick() {
		submitButton.click();
	}
	
	public String getStatus() {
		return status.getText();
	}
	
	public void goRegistration() {
		addUserNav.click();
	}
	
	public void goLogin() {
		LoginNav.click();
	}
	
	public void sleep(int sec) throws InterruptedException {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, SECONDS)
				.pollingEvery(5, SECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement foo = wait.until(new Function<WebDriver, WebElement>()
		{
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.name("TEST"));
			}
		});
				
	}
}
