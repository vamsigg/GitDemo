package Automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Automation.abstractcomponents.abstractcomponent;

public class Checkoutpage extends abstractcomponent {

    WebDriver driver;
    
	public Checkoutpage(WebDriver driver)
	{
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectcountry;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectcountry(String countryname)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,countryname).build().perform();
		waitforelementtoappear(By.cssSelector(".ta-results"));
		selectcountry.click();
	}
	
	public conformationpage submitorder()
	{
		 WebElement e = driver.findElement(By.cssSelector(".btnn"));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click(0);", e);   
		 
		//submit.click();
		 
		return new conformationpage(driver);
	}
}
