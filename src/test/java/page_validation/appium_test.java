package page_validation;

import java.io.File;
import java.net.URL;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class appium_test {
	
	@Test
	public void appium_test_method() throws Throwable {
		AppiumDriverLocalService srvc = null;
		try {
			srvc = new AppiumServiceBuilder().withAppiumJS(new File("C:/Users/sayan/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723).build();
			srvc.start();
			String installed_drivers = srvc.getDriverProperty();
			System.out.println("installed_drivers = " + installed_drivers);
			//URL appium_server_url = new URL("http://127.0.0.1:4723");
			UiAutomator2Options options = new UiAutomator2Options();
			options.setDeviceName("Sayan-emulator");
			options.setApp(System.getProperty("user.dir") + "/src/test/java/resources/ApiDemos-debug.apk");
			options.setCapability("automationName", "UiAutomator2");
			AndroidDriver android_driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
			android_driver.quit();
			srvc.stop();
		}
		catch(Exception e) {
			srvc.stop();
			e.printStackTrace();
		}
		
	}

}
