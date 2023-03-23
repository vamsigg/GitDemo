package Automation.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import Automation.Testcomponents.BaseTest;
import Automation.Testcomponents.Retry;
import Automation.pageobjects.Cartpage;
import Automation.pageobjects.ProductCatalogue;

public class errorvalidation extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)//if we want to retry in case off failure
	public void loginerrorvalidation() throws IOException{
		
		String productname="ZARA COAT 3";
	    landingpage.loginapplication("iqaa@gmail.com","a738218@Ggvk");	
		Assert.assertEquals("Incorrect email or password.",landingpage.geterrormsg());
	}
	
	@Test
	public void producterrorvalidation() throws IOException{
		
		String productname="ZARA COAT 3";
		
		ProductCatalogue productcatalogue=landingpage.loginapplication("iqa@gmail.com","738218@Ggvk");	
		productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
		Cartpage cartpage=productcatalogue.gotocartpage();
		
		Boolean match=cartpage.verifyproductdisplay("ZARA COAT 33");
		Assert.assertFalse(match);
    }
}