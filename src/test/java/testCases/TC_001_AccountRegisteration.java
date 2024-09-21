package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.registerPage;
import testBase.baseCases;

public class TC_001_AccountRegisteration extends baseCases
{
	@Test(groups={"Sanity","Master"})
	public void Registerationtest()
	{
		try
		{
		logger.info("******* Starting TC_001_AccountRegisteration ");
		
		homePage hp = new homePage(driver);
		logger.info("Clicking on My account....");
		hp.clickMyAccount();
		logger.info("Clicking on Register....");
		hp.clickRegister();
		//logger.info("Clicking on Login....");
		//hp.clicklogin();
		
		
		registerPage rp = new registerPage(driver);
		logger.info("Providing user details....");
		rp.setfirstname(randomstring().toUpperCase());
		rp.setlastname(randomstring().toUpperCase());
		rp.setemail(randomstring()+"@gmail.com");
		rp.settelephone(randomnumber());
		String pwd = randomalphanumneric();
		rp.setpassword(pwd);
		rp.setconfirmpassword(pwd);
		rp.ppbtnclick();
		rp.continuebtnclick();
		
		logger.info("Validating expected message on page....");
		String confirmmsg = rp.getconfirmationmessage();
		if(confirmmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed....");
			logger.debug("debug logs....");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmmsg, "Your Account Has Been Created!!!!!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("******* Finished TC_001_AccountRegisteration ");

	}
	
}
