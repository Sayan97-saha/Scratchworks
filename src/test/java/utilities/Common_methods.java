package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page_validation.base_class;

public class Common_methods extends base_class{
	
	public boolean click_on(WebElement element) {
		boolean res = false;
		try {
			base_class.actions_obj.moveToElement(element).build().perform();
			element.click();
			res = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public boolean send_values(WebElement element, String str_value) {
		boolean res = false;
		try {
			base_class.actions_obj.moveToElement(element).build().perform();
			element.sendKeys(str_value);
			res = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void setup_wait() throws Throwable {
		try {
			int wait_time = Integer.valueOf(Read_config.get_from_config("wait_time"));
			base_class.wait_obj = new WebDriverWait(base_class.driver, 
					Duration.ofSeconds(wait_time));
			System.out.println("Explicit wait has been setup as " + wait_time + " seconds");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean custom_wait(String wait_type, WebElement element) {
		boolean res = false;
		try {
			switch(wait_type) {
			case("visible"):
				base_class.wait_obj.until(ExpectedConditions.visibilityOf(element));
				break;
			case("clickable"):
				base_class.wait_obj.until(ExpectedConditions.elementToBeClickable(element));
				base_class.actions_obj.moveToElement(element).build().perform();
				break;
			}
			res = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public WebElement find_element(String xpath_val) {
		WebElement element = null;
		try {
			element = base_class.driver.findElement(By.xpath(xpath_val));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	public List<WebElement> find_elements(String xpath_val) {
		List<WebElement> elements = null;
		try {
			elements = base_class.driver.findElements(By.xpath(xpath_val));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return elements;
	}
	
	public String get_from_element(WebElement element, String decision_attr) {
		String result_val = null;
		try {
			switch(decision_attr) {
			case("text"):
				result_val = element.getText();
				break;
			case("tagname"):
				result_val = element.getTagName();
				break;
			default:
				result_val = element.getAttribute(decision_attr);
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result_val;
	}

}