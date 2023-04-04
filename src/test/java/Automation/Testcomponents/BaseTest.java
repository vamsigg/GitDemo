package Automation.Testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.pageobjects.Landingpage;

public class BaseTest {

	public WebDriver driver;
	public Landingpage landingpage;
	
	public WebDriver initialisedriver() throws IOException
	{	
		Properties pro=new Properties();           //to get the browser information
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\resourses\\globaldata.properties");
		pro.load(fis);
		
		String browsename = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser");
			                                                   //java turnery operator  //               
		//String browsename=pro.getProperty("browser");
		
		if(browsename.contains("chrome"))    //for avoiding conflict with headless "chromeheadless"
		{
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("--remote-allow-origins=*");
			if(browsename.contains("headless")) //if we don't mention this chrome also considered as headless
			{
			options.addArguments("headless");   //for running in headlessmode // for speed
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400,900));//full screen
	    	//WebDriverManager.chromedriver().setup();
	        //driver=new ChromeDriver();
		} 
		else if(browsename.equalsIgnoreCase("firefox"))
		{
			FirefoxOptions options = new FirefoxOptions(); 
			driver=new FirefoxDriver(options);
			//System.setProperty("webdriver.gecko.driver","C:\\Users\\mfcwl\\Downloads\\geckodriver_win64\\geckodriver.exe");
			//WebDriver driver=new FirefoxDriver();
		}
		else if(browsename.equalsIgnoreCase("edge"))
		{
			//edgecode
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}	
	
	@BeforeMethod(alwaysRun=true)        // we want to run this methods irrespective of the conditions
	public Landingpage launchapplication() throws IOException
	{
		driver=initialisedriver();
		landingpage=new Landingpage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)   
	public void teardown()
	{
		driver.close();
	}
	
	public String getscreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//screenshots//"+ testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//screenshots//"+ testCaseName +".png";    //retuning thepath
	}
	
	public List<HashMap<String, String>> getjsondatatomap(String filepath) throws IOException
	{
		//read json to string
		String JsonContent=FileUtils.readFileToString(new File(filepath)
				,StandardCharsets.UTF_8);
	    
		//string to hashmap--->jackson databend is required
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}
}
