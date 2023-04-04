package Automation.Testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{   //to handle failures because of  inconsistensy

	int count=0;
	int maxTry=1;
	
	@Override
	public boolean retry(ITestResult result) {  //as long this method retun true it will re run
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
