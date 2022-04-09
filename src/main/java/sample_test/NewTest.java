package sample_test;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NewTest {
	public WebDriver driver;
	
  @Test
  public void simple_test() {
	  System.setProperty("webdriver.chrome.driver", "web_drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Opening Chrome");
	  
  }

  @AfterMethod
  public void afterTest() {
	  System.out.println("Closing Chrome");
	  driver.close();

  }

}
