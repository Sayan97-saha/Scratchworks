package page_validation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Open_lesson {
	
	@FindBy(xpath = "//button[text() = 'Continue']")
	private WebElement continue_btn;

}
