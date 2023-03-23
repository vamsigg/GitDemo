package Automation.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Automation.resourses.ExtentreportNG;

public class Listeners extends BaseTest implements ITestListener
{
	    ExtentTest test;
	    ExtentReports extent=ExtentreportNG.getreportobject();	
	    ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();  //to avoid overlap while running in parallel
	    
	    @Override		
	    public void onTestStart(ITestResult result)                        //before starting the excution	
	    {				
	        test=extent.createTest(result.getMethod().getMethodName());    //result will get the details of the test
	        extenttest.set(test);  //we are pushing it in to thread local  //thread local will assign unique thread id
	    }	
	    
	    @Override		
	    public void onTestSuccess(ITestResult result) {					
	        			
	    	extenttest.get().log(Status.PASS, "Test Passed");		
	    }	
	    
	    @Override		
	    public void onTestFailure(ITestResult result) {					
	        				
	    	//test.log(Status.FAIL, "Test faled");
	    	
	    	extenttest.get().fail(result.getThrowable()); //we are failing you for reaching this block
	    	
	    	//screenshot//attach to the report
	    	try //to get the driver info
	    	{
				driver=(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} 
	    	catch(Exception e1) 
	    	{
				e1.printStackTrace();
	    	}
	    	
	    	String filepath = null;
			try
			{
				filepath = getscreenshot(result.getMethod().getMethodName(),driver);//particular test driver info is also stored here
			}
			catch (IOException e)
			{	
				e.printStackTrace();
			}
			extenttest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());//add screenshot to extent report  	
	    }
	    
	    @Override		
	    public void onFinish(ITestContext context) {					
	        			
	        extent.flush();
	    }
}
