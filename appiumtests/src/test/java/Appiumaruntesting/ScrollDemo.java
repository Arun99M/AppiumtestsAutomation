package Appiumaruntesting;


import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {
	
	@Test
	
	public void ScrollDemoTest()throws MalformedURLException, InterruptedException
	{
		
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		
		//where to scroll is known prior
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
		
		//No prior idea till were to scroll
		scrollToEndAction();
		
	}


}
