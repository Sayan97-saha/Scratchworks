package selenium_glue_code;

import org.testng.Assert;

import io.cucumber.java.*;
import io.cucumber.java.en.*;
import page_validation.*;
import utilities.driver_manager;

public class Stepdef extends BaseClass{
	
	Login_w3schools pfc = null;
	Open_tutorial ot = null;
	
	
	
	@Before
	public void before_any_method(Scenario scenario) {
		initialize_before_methods(scenario);
	}
	
	
	@Given("User launches browser and goes to the default Google home page")
	public void user_launches_browser_and_goes_to_the_default_google_home_page() {
		driver = driver_manager.create_driver("chrome");
		driver.get("https://www.google.com/");
		capture_screenshot();
		report_log("Successfully launched browser");
	}	
	
	@When("User navigates to the W3Schools webpage")
	public void user_navigates_to_the_webpage() {
	    driver.navigate().to("https://www.w3schools.com/");
	    capture_screenshot();
	    report_log("Successfully navigated to the W3Schools website");
	}
	
	@Then("User logs in using credentials")
	public void user_logs_in() {
	    pfc = new Login_w3schools(driver);
	    Assert.assertTrue(pfc.login_to_W3Schools());
	}
	
	@Given("User opens up the java tutorial")
	public void user_opens_up_the_java_tutorial() {
		ot = new Open_tutorial(driver);
		Assert.assertTrue(ot.click_on_java_tutorial());
	}
	
	@Given("User closes the browser")
	public void user_closes_the_browser() {
		driver.quit();
		report_log("Successfully closed browser");
	}
	
	
	
}
