package page_validation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium_glue_code.BaseClass;

public class Login_w3schools extends BaseClass{
	
	
	@FindBy(xpath = "//a[@id='w3loginbtn']")
	private WebElement login_link;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement email;
	
	@FindBy(xpath = "//input[@name='current-password']")
	private WebElement password;
	
	@FindBy(xpath = "//span[text() = 'Log in']//parent::button")
	private WebElement login_btn;
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	public Login_w3schools(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean login_to_W3Schools() {
		boolean res = false;
		
		try {
			login_link.click();
			wait.until(ExpectedConditions.visibilityOf(email));
			capture_screenshot();
			email.sendKeys("sayansaha1797@gmail.com");
			password.sendKeys("Sayan17101997!");
			capture_screenshot();
			login_btn.click();
			report_log("Successfully logged in");
			res = true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
		return res;
	}

}
