package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccount;
import testBase.baseCases;
import utilities.DataProviders;

public class TC_003_LoginDDT extends baseCases
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups={"Regression","Master"})
	public void verify_Loginddt(String email, String pwd, String exp)
	{
		//logger.info("Starting TC_003_DDT...");
		try
		{
		homePage hp = new homePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		loginPage lp = new loginPage(driver);
		lp.setloginusername(email);
		lp.setloginpassword(pwd);
		lp.clickloginbtn();
		
		myAccount macc = new myAccount(driver);
		boolean targetpage = macc.isMyAccountpageexists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				macc.ClickAccountpageMyaccountbutton();
				macc.Clicklogoubutton();
				macc.Clicklogoutcontinuebutton();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetpage==true)
			{
				macc.ClickAccountpageMyaccountbutton();
				macc.Clicklogoubutton();
				macc.Clicklogoutcontinuebutton();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		//logger.info("Ending TC_003_DDT...");
		
	}	
}







