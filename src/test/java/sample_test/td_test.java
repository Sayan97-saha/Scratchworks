package sample_test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class td_test {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "web_drivers/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("file:///C:/Users/Sayan/Desktop/test_website.html");
		
		WebElement table_row = driver.findElement(By.xpath("//th[contains(text(), 'row2')]//parent::tr"));
		List<WebElement> td1 = table_row.findElements(By.xpath("td")); 
		List<WebElement> td2 = driver.findElements(By.xpath("//td"));
		List<WebElement> td3 = table_row.findElements(By.tagName("td"));
		
		System.out.println("td1 = "+td1.size());
		System.out.println("td2 = "+td2.size());
		System.out.println("td3 = "+td3.size());
	}

}
