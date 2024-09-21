package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage
{
	public homePage(WebDriver driver) 
	{
		super(driver);		
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement linkmyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement linklogin;
	
	public void clickMyAccount()
	{
		linkmyAccount.click();
	}
	
	public void clickRegister()
	{
		linkRegister.click();
	}
	
	public void clicklogin()
	{
		linklogin.click();
	}	
}
