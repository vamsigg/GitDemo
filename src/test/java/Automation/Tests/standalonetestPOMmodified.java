package Automation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Automation.Testcomponents.BaseTest;
import Automation.pageobjects.Cartpage;
import Automation.pageobjects.Checkoutpage;
import Automation.pageobjects.Orderspage;
import Automation.pageobjects.ProductCatalogue;
import Automation.pageobjects.conformationpage;
import Automation.resourses.XLUtility;

public class standalonetestPOMmodified extends BaseTest{

	@Test
	public void standalone() throws IOException
	{
		String productname="IPHONE 13 PRO";
		ProductCatalogue productcatalogue=landingpage.loginapplication("iqa@gmail.com","738218@Ggvk");	
		productcatalogue.getproductlist();
		productcatalogue.addproducttocart(productname);
		Cartpage cartpage=productcatalogue.gotocartpage();
		
		Boolean match=cartpage.verifyproductdisplay(productname);
		Assert.assertTrue(match);
		Checkoutpage Checkoutpage=cartpage.gotocheckout();
		Checkoutpage.selectcountry("india");
		conformationpage conformationpage=Checkoutpage.submitorder();
		String conformmessage=conformationpage.getconformationmsg();
	    Assert.assertTrue(conformmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dataProvider="getData",dependsOnMethods= {"standalone"})
	public void orderhistory(String email,String password,String product)
	{
		//to verify zara coat 3 is displaying in the order page
		ProductCatalogue productcatalogue=landingpage.loginapplication(email,password);
		Orderspage orderspage=new Orderspage(driver);
		orderspage.gotoorderspage();
		Assert.assertTrue(orderspage.verifyorderdisplay(product));
	}
	
	@DataProvider
	public String[][] getData() throws IOException
	{
		String path=".\\src\\test\\java\\Automation\\Data\\datasheet.xlsx";
		
		XLUtility xlutil=new XLUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)     //1
		{		
			for(int j=0;j<totalcols;j++)  //0
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}	
		
	    return logindata;				
	}	
	
	
/*	@Test(dataProvider="getdata",dependsOnMethods= {"standalone"})
	public void orderhistory(String email,String password,String product)
	{
		//to verify zara coat 3 is displaying in the order page
		ProductCatalogue productcatalogue=landingpage.loginapplication(email,password);
		Orderspage orderspage=new Orderspage(driver);
		orderspage.gotoorderspage();
		Assert.assertTrue(orderspage.verifyorderdisplay(product));
	}
	
    @DataProvider
	public Object[][] getdata()
	{
		return new Object[][]  {{"iqa@gmail.com","iqa@gmail.com","IPHONE 13 PRO"},{"iqa@gmail.com","738218@Ggvk","IPHONE 13 PRO"}};
	}*/
	
	
	
	
/*	//if we have more parameters will go for hashmap

    @Test(dataProvider="getdata",dependsOnMethods= {"standalone"})
	public void orderhistory(HashMap<String,String> input)
	{
		//to verify zara coat 3 is displaying in the order page
		ProductCatalogue productcatalogue=landingpage.loginapplication(input.get("email"),input.get("password"));
		Orderspage orderspage=new Orderspage(driver);
		orderspage.gotoorderspage();
		Assert.assertTrue(orderspage.verifyorderdisplay(input.get("product")));
	}
	
    @DataProvider
	public Object[][] getdata() throws IOException
	{
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "iqa@gmail.com");
		map.put("password", "738218@Ggvk");
		map.put("product", "IPHONE 13 PRO");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "iqa@gmail.com");
		map1.put("password", "738218@Ggvk");
		map1.put("product", "IPHONE 13 PRO");
			
		return new Object[][]  {{map},{map1}};
	}*/
    
	
	
    //this hashmaps will automatically create by scaning json
	
	/*@Test(dataProvider="getdata",dependsOnMethods= {"standalone"})
	public void orderhistory(HashMap<String,String> input)
	{
	//to verify zara coat 3 is displaying in the order page
	ProductCatalogue productcatalogue=landingpage.loginapplication(input.get("email"),input.get("password"));
	Orderspage orderspage=new Orderspage(driver);
	orderspage.gotoorderspage();
	Assert.assertTrue(orderspage.verifyorderdisplay(input.get("product")));
	}
		

    @DataProvider
	public Object[][] getdata() throws IOException
	{
    List<HashMap<String,String>> data=getjsondatatomap(System.getProperty("user.dir")+"\\src\\test\\java\\Automation\\Data\\orderdata.json");
    return new Object[][]  {{data.get(0)},{data.get(1)}};
	}*/
}

