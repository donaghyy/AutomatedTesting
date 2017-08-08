package LoginTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(name = "username")
	private WebElement usernameInput;
	
	@FindBy(name = "password")
	private WebElement passwordInput;
	
	@FindBy(name = "FormsButton2")
	private WebElement submitButton;

	@FindBy(css = "small > a:nth-child(6)")
	private WebElement addUserNav;
	
	@FindBy(css = "small > a:nth-child(7)")
	private WebElement LoginNav;
	
	@FindBy(css = "body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b")
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
		TimeUnit.SECONDS.sleep(sec);
	}
}
