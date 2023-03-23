package Automation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.abstractcomponents.abstractcomponent;

public class ProductCatalogue extends abstractcomponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By productsby=By.cssSelector(".mb-3");
	
	@FindBy(css=".mb-3")
	List<WebElement> productslist;
	
	By addtocaart=By.cssSelector(".card-body button:last-of-type");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By toastmesg=By.cssSelector("#toast-container");
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	public List<WebElement> getproductlist()
	{
		waitforelementtoappear(productsby);
		return productslist;
	}
	
	public WebElement getproductbyname(String productname)
	{
		WebElement prod= getproductlist().stream().filter(product->product.findElement(By.cssSelector("b"))
				        .getText().equals(productname)).findFirst().orElse(null);      
        return prod;
	}
	
	public void addproducttocart(String productname)
	{
		WebElement prod=getproductbyname(productname);
		prod.findElement(addtocaart).click();
		waitforelementtoappear(toastmesg);
		waitforelementtodisappear(spinner);
	}
	
	public Cartpage gotocartpage()
	{
		cartHeader.click();
		Cartpage cartpage=new Cartpage(driver);
		return cartpage;
	}
}
