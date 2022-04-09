package sample_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample_test {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "web_drivers/chromedriver.exe");
		
		ChromeDriver driver = new ChromeDriver();
		WebDriver driver1 = new ChromeDriver();
		driver.manage().window().maximize();
		driver1.close();
		driver.get("https://www.google.com");
		driver.close();
		

	}

}
