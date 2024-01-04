package Appiumaruntesting;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_3_Hybrid extends BaseTest{
	

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
		
		/*get(0) is given both elements above and down that is considered for first index once validated then for next
		element the index will be considering 0 only, in this case i have to add 2 carts this scenario got usefull.*/
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
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();//in app if we click it is redirecting to the web page for doing payment kind of things so this is called hybrid.  
		Thread.sleep(6000);
		
		/*Switch your context to browser from the native app, the driver know that no more at native app landed on the web app.
		beacuse till now native app is exectuing on Androiddriver */
		
		//From here the web browser part working .
		Set<String>contexts = driver.getContextHandles();//getting the context like native app or web view app
		
		for (String contextName : contexts)  //1st index contexts getting and printing in the context name and the loop iterates till the context retrieves
		{
			System.out.println(contextName);
		}
		
		    //the context name we should ask with developer which mention in the code to put correct one because this is hybrid, then the drivers will switch to web mode. 
		
		//Run the above for loop and get the contexts for Web view, if unknow cases, better write for loop and get in output copy and paste in below driver context "WEBVIEW_com.androidsample.generalstore"
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		//Google locators element		
		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));  // if i want to come back from web view to native app then the drivers should know, so change the context to native app
		driver.context("Native_App");
		
		//2 important thinks need to understand get context handles and correct chrome driver to be choosed and added in the UIAutomator class for execute it.
		
	}
}



