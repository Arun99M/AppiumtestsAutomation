package Appiumaruntesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc_2 extends BaseTest{

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
		
		//driver.findElement(By.xpath("(//android.widget.TextView[@text='ADDED TO CART'])[1]")).click();
							//(or)
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
		
		//get(0) is given both elements above and down that is considered for first index once validated then for next element the index will be considering 0 only, in this case i have to add 2 carts this scenario got usefull.
		Thread.sleep(2000);
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
		
		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		
		int count = productPrices.size();
		double totalsum = 0;
		for (int i=0; i<count; i++)
		{
			String amountString = productPrices.get(i).getText();  
			Double price = getFormattedAmount(amountString);  
			totalsum = totalsum + price ; // 160.97 +120.97
			
		}
		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		Double displayFormattedSum = getFormattedAmount(displaySum);
		
		Assert.assertEquals(totalsum, displayFormattedSum);
		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);
		driver.findElement(By.id("android:id/button1")).click();
		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		//driver.findElement(By.id("com.androidsample.generalstore:id/productPrice"));
		Thread.sleep(3000);
	}
	}


