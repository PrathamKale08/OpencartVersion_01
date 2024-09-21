package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccount;
import testBase.baseCases;

public class TC_002_LoginTest extends baseCases 
{
	@Test(groups={"Regression","Master"})
	public void LoginTest()
	{
		try
		{
			logger.info("******* Starting TC_002_login test ");
			homePage hp = new homePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();
		
			loginPage lp = new loginPage(driver);
			logger.info("Setting username...");
			lp.setloginusername(p.getProperty("username"));
			logger.info("Setting password...");
			lp.setloginpassword(p.getProperty("password"));
			//lp.setloginpassword(p.getProperty("passwordshdkhsc"));
			logger.info("clicking on login button...");
			lp.clickloginbtn();
		
			myAccount macc = new myAccount(driver);
			logger.info("confirming successful login...");
			macc.getMyAccountConfirmation();
			String MyAccountconfirmmessage = macc.getMyAccountConfirmation();
			if(MyAccountconfirmmessage.equals("My Account"))
				{
					Assert.assertTrue(true);
				}
			else
				{
					Assert.assertTrue(false);
					Assert.fail();
					logger.debug("debugs logs...");
					logger.info("Test cases failed...");
				}
			
			macc.ClickAccountpageMyaccountbutton();
			macc.Clicklogoubutton();
			macc.Clicklogoutcontinuebutton();
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******* Finished TC_002_Login test");

		
	}

}
