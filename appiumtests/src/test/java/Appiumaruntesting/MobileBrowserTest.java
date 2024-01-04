package Appiumaruntesting;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {
	
	@Test
	
	public void broswertest() throws InterruptedException
	
	{
	//driver.get("http://google.com");
	//driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
	//driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
		driver.findElement(By.cssSelector("a[routerlink*='product']")).click();  //if * is mentioned on locator to regular expression if not giving * exact special characters should given
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)","");
		String text = driver.findElement(By.cssSelector("a[href*='products/3']")).getText(); //if * is mentioned on locator to regular expression if not giving * exact special characters should given
		Assert.assertEquals(text, "Devops");
	
	Thread.sleep(4000);
	}

}
