package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registerPage extends basePage
{
	public registerPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtfirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement texttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btnPP;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirnmationmsg;
	
	public void setfirstname(String firstname)
	{
		txtfirstname.sendKeys(firstname);
	}
	public void setlastname(String lastname)
	{
		txtlastname.sendKeys(lastname);
	}
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void settelephone(String telephone)
	{
		texttelephone.sendKeys(telephone);
	}
	public void setpassword(String password)
	{
		txtpassword.sendKeys(password);
	}
	public void setconfirmpassword(String password)
	{
		txtConfirmpassword.sendKeys(password);
	}
	public void ppbtnclick()
	{
		btnPP.click();
	}
	public void continuebtnclick()
	{
		btncontinue.click();
	}
	
	public String getconfirmationmessage()
	{
		try
		{
			return(confirnmationmsg.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
		
	}
	

}
