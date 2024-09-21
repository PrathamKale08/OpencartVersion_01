package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage
{
	public loginPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtloginusername;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtloginpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	
	public void setloginusername(String email)
	{
		txtloginusername.sendKeys(email);
	}
	
	public void setloginpassword(String pwd)
	{
		txtloginpassword.sendKeys(pwd);
	}
	
	public void clickloginbtn()
	{
		loginbtn.click();
	}

}
