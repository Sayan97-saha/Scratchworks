package glue_code;


import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_validation.*;
import utilities.*;

public class selenium_gluecode extends base_class{
	
	@Before
	public void before_method(Scenario scenario)throws Throwable{
		initialize_scenario(scenario);
	}
	
	Home_Page home_page = null;
	
	@Given("User is preparing to test {string}")
	public void user_is_preparing_to_test(String test_keyword) throws Throwable {
		 
		scenario_keyword = test_keyword;
		driver = Driver_manager.create_driver("chrome");
		actions_obj = new Actions(driver);
		home_page = new Home_Page(driver);
		cmn_mthds = new Common_methods();
		cmn_mthds.setup_wait();
		String url_val = Read_config.get_from_config("url");
		driver.navigate().to(url_val);
		step_log(url_val + " opened successfully!");
		capture_screenshot();
		Assert.assertEquals(Read_config.get_from_config("expected_page_title"), driver.getTitle());
	}

	@When("User closes the login popup")
	public void test_step_one() throws Throwable {
		Assert.assertTrue(home_page.close_login_popup());
	}
	
	@When("User clicks on the {string} link")
	public void link_check(String parameter_val) throws Throwable {
		Assert.assertTrue(home_page.link_click_chk(parameter_val));
	}

	@Then("test step_two")
	public void test_step_two() {
		System.out.println("step 2");
	}

}
