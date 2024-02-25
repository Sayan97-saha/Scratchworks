package page_validation;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.Scenario;
import utilities.Common_methods;

public class base_class {
	
	public static WebDriver driver;
	public static String scenario_keyword = "";
	public static Actions actions_obj = null;
	public static WebDriverWait wait_obj = null;
	public static Scenario scenario;
	public static Common_methods cmn_mthds = null;
	
	
	
	
	
	
	
	
	@BeforeTest
	public void initialize_scenario(Scenario scenario) {
		base_class.scenario = scenario;
	}
	
	public static void step_log(String log_msg) {
		scenario.log(log_msg);
	}
	
	public static void capture_screenshot() {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "screenshot");
	}

}
