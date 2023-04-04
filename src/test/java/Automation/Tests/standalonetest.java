package Automation.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalonetest {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver=new ChromeDriver(options);
		Thread.sleep(10000);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("iqa@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("738218@Ggvk");
		driver.findElement(By.id("login")).click();
		
		String productname="IPHONE 13 PRO";
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));	
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText()
				        .equals(productname)).findFirst().orElse(null);      //if we want webelement use filter
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
		
	//	Actions a=new Actions(driver);
	//	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	    driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	   	 
	    WebElement e = driver.findElement(By.cssSelector(".btnn"));
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	    js.executeScript("arguments[0].click(0);", e);   
	   
	    String conformmsg=driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
	    Assert.assertEquals(conformmsg,"THANKYOU FOR THE ORDER.");
	    //Assert.assertTrue(conformmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    
	    driver.close();
	}
}
