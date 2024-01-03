package appiumtests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;

public class CalculatorTest {

	static AppiumDriver driver;
	
	public static void main(String[] args) throws MalformedURLException  {
		openCalculator();
		
	}
	
	public static void openCalculator() throws MalformedURLException{
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability("deviceName", "samsung SM-F127G"); 
		cap.setCapability("udid", "RZ8T6175X1N");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "13");
		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		
		driver = new AppiumDriver(url, cap);
		System.out.println("Application started...");
	}

}
