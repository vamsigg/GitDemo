package Automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.abstractcomponents.abstractcomponent;

public class conformationpage extends abstractcomponent {

	WebDriver driver;
	
	public conformationpage(WebDriver driver)
	{
		super(driver);
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement conformationmsg;
	
	public String getconformationmsg() 
	{
		return conformationmsg.getText();
	}
}
