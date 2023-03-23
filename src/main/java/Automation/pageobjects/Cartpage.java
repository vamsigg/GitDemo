package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.abstractcomponents.abstractcomponent;

public class Cartpage extends abstractcomponent {

	WebDriver driver;
	
	public Cartpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li[class='totalRow'] button[type='button']")
	WebElement checkoutele;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> carproducts;
	
	public Boolean verifyproductdisplay(String Productname)
	{
		Boolean match=carproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
	}
	
	public Checkoutpage gotocheckout()
	{
		checkoutele.click();
		Checkoutpage checkoutpage=new Checkoutpage(driver);
		return checkoutpage;
	}
}
