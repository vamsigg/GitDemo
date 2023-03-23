package Automation.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.Testcomponents.BaseTest;
import Automation.pageobjects.Cartpage;
import Automation.pageobjects.Checkoutpage;
import Automation.pageobjects.Landingpage;
import Automation.pageobjects.ProductCatalogue;
import Automation.pageobjects.conformationpage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdefinitionimp extends BaseTest{

	public Landingpage landingpage;
	public ProductCatalogue productcatalogue;
	public conformationpage conformationpage;
	
/*	@Given("I landed on the ecommerse page")
	public void I_landed_on_the_ecommerse_page() throws IOException
	{
		landingpage=launchapplication();
	}
	
	@Given("^Logged in with username(.+) and password(.+)$")
	public void Logged_in_with_username_and_password(String username,String password)
	{
		productcatalogue=landingpage.loginapplication(username,password);
	}
	
	@When("^I add product(.+) to cart$")
	public void I_add_product_to_cart(String productname)
	{
		List<WebElement> products=productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
	}
	
	@And("^checkout (.+) and submit the order$")                //@when we can use any one based on previous one
	public void checkout_and_submit_the_order(String productname)
	{
        Cartpage cartpage=productcatalogue.gotocartpage();		
		Boolean match=cartpage.verifyproductdisplay(productname);
		Assert.assertTrue(match);
		Checkoutpage Checkoutpage=cartpage.gotocheckout();
		Checkoutpage.selectcountry("india");
		conformationpage=Checkoutpage.submitorder();
	}
	
	@Then("{string} message is displayed on the conformation page")
	public void message_is_displayed_on_the_conformation_page(String string)
	{
		String conformmessage=conformationpage.getconformationmsg();
	    Assert.assertTrue(conformmessage.equalsIgnoreCase(string));
	    driver.close();
	} */
	
	
	@Given("^I landed on the ecommerse page$")
    public void i_landed_on_the_ecommerse_page() throws Throwable {
		landingpage=launchapplication();
    }

    @When("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String username, String password) throws Throwable {
    	productcatalogue=landingpage.loginapplication(username,password);
    }

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productname) throws Throwable {
		productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
	}

	@And("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productname) throws Throwable {
		Cartpage cartpage=productcatalogue.gotocartpage();		
		Boolean match=cartpage.verifyproductdisplay(productname);
		Assert.assertTrue(match);
		Checkoutpage Checkoutpage=cartpage.gotocheckout();
		Checkoutpage.selectcountry("india");
		conformationpage=Checkoutpage.submitorder();
	}
	
	@Then("^\"([^\"]*)\" message is displayed on the conformation page$")
	public void something_message_is_displayed_on_the_conformation_page(String strArg1) throws Throwable {
		String conformmessage=conformationpage.getconformationmsg();
	    Assert.assertTrue(conformmessage.equalsIgnoreCase(strArg1));
	    driver.close();
	}

	


    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
    	Assert.assertEquals("Incorrect email or password.",landingpage.geterrormsg());
    	driver.close();
    }
	
	
}
