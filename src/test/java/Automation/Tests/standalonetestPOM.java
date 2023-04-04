package Automation.Tests;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import Automation.pageobjects.Cartpage;
import Automation.pageobjects.Checkoutpage;
import Automation.pageobjects.Landingpage;
import Automation.pageobjects.ProductCatalogue;
import Automation.pageobjects.conformationpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class standalonetestPOM{

	@Test
	public void standalone() throws IOException{
		
		String productname="IPHONE 13 PRO";
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\mfcwl\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		Landingpage landingpage=new Landingpage(driver);
		landingpage.goTo();
		
		ProductCatalogue productcatalogue=landingpage.loginapplication("iqa@gmail.com","738218@Ggvk");	
		productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
		
		Cartpage cartpage=productcatalogue.gotocartpage();	
		Boolean match=cartpage.verifyproductdisplay(productname); //but here we are doing assertion so use 
		Assert.assertTrue(match);
		
		Checkoutpage Checkoutpage=cartpage.gotocheckout();
		Checkoutpage.selectcountry("india");
		
		conformationpage conformationpage=Checkoutpage.submitorder();
		String conformmessage=conformationpage.getconformationmsg();
	    Assert.assertTrue(conformmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	    
	    driver.close();
	}
}
