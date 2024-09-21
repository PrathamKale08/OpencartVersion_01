package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccount extends basePage 
{
	public myAccount(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgheading;
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement AccountpageMyaccountbutton;
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement MyAccountconfirmation;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement logoutbutton;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement logoutcontinuebutton;
	
	
	public boolean isMyAccountpageexists()
	{
		try  
		{
		return(msgheading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getMyAccountConfirmation()
	{
		String MyAccountConfirmation = MyAccountconfirmation.getText();
		return MyAccountConfirmation;		
	}
	
	public void ClickAccountpageMyaccountbutton()
	{
		AccountpageMyaccountbutton.click();
	}
	
	public void Clicklogoubutton()
	{
		logoutbutton.click();
	}
	
	public void Clicklogoutcontinuebutton()
	{
		logoutcontinuebutton.click();
	}

	
	

}
