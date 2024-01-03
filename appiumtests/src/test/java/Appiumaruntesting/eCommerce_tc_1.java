package Appiumaruntesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc_1 extends BaseTest{
	
	@Test
	public void FillForm() throws InterruptedException
	{
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.hideKeyboard();  //this is used for hiding the screen when keyboard is appears on screen
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Arunkumar");
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		
		//String toastMessage = driver.findElement(By.xpath("//android.widget.Toast)[1]")).getAttribute("name"); //Toast Message or error message		
		//Assert.assertEquals(toastMessage, "Please enter your name");
		
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

		
	int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
	
	for (int i = 0; i<productCount; i++)
	{
		String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
		if (productName.equalsIgnoreCase("Jordan 6 Rings"))
		{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		//WebDriver wait is added -  It allows you to define custom wait conditions and specify the maximum duration for the WebDriver to wait until the condition is satisfied
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		
		String lastPageProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		Assert.assertEquals(lastPageProduct, "Jordan 6 Rings");
		
	}
	}

}
