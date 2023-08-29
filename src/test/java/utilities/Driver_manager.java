package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import page_validation.base_class;

public class Driver_manager extends base_class{
	
	public static WebDriver create_driver(String browser_name) {
		System.out.println("Inside create_driver --->");
		try {
			if(browser_name.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:/IT/java_practice/framework_build_project/test_dev_project/chromedriver.exe");
				ChromeOptions chrome_options = new ChromeOptions();
//				chrome_options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
				driver = new ChromeDriver(chrome_options);
				driver.manage().window().maximize();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("create_driver completed --->");
		return driver;
	}

}
