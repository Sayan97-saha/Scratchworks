package page_validation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import selenium_glue_code.BaseClass;

public class Open_tutorial extends BaseClass {
	
	@FindBy(xpath = "//img[@alt='Java']")
	private WebElement java_tutorial_widget;
	
	
	@SuppressWarnings("unused")
	private WebDriver driver;
	public Open_tutorial(WebDriver driver) {
		this.driver = driver;
		//wait = new WebDriverWait(driver, 60);
		a = new Actions(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean click_on_java_tutorial() {
		boolean res = false;
		try{
			wait.until(ExpectedConditions.visibilityOf(java_tutorial_widget));
			wait.until(ExpectedConditions.elementToBeClickable(java_tutorial_widget));
			a.moveToElement(java_tutorial_widget).build().perform();
			capture_screenshot();
			java_tutorial_widget.click();
			Thread.sleep(5000);
			capture_screenshot();
			report_log("Successfully opened the Java tutorial");
			res = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

}
