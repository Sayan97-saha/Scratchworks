package selenium_glue_code;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;

public class BaseClass {
	
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected Actions a;
	protected static Scenario scenario;
	
	
	public static void initialize_before_methods(Scenario scenario) {
		BaseClass.scenario = scenario;
	}
	
	public static void capture_screenshot(){
	     byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	          scenario.attach(screenshot, "image/png", "screenshot");
	}
	
	public static void report_log(String string) {
		scenario.log(string);
	}



	
	
	
	
}
