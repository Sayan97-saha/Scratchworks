package utilities;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class driver_manager {
	
	static WebDriver driver;
	
	public static WebDriver create_driver(String browsertype) {
		
		if(browsertype.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "web_drivers/chromedriver.exe");
			String download_path = System.getProperty("user.dir") + "/automation_downloads/";
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			
			prefs.put("default.download_directory", download_path.replace("/", "\\"));
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.prompt_for_download", false);
			prefs.put("plugins.always_open_pdf_externally", true);
			
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--start-maximized", "--disable-notifications", "--disable-infobars", "--disable-print-preview");
			//options.addArguments("--user-agent = Chrome/97.0.4692.71");
			options.addArguments("--disable-gpu");
			options.setExperimentalOption("prefs", prefs);
			options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
			
			DesiredCapabilities cap = new DesiredCapabilities();			
			
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			
			options.merge(cap);
			
			driver = new ChromeDriver(options);
			
		}
		else if(browsertype.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "web_drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browsertype.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "web_drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().deleteAllCookies();
		
		return driver;
	}

}
