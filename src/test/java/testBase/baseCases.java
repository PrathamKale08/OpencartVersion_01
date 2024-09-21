package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class baseCases 
{
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(@Optional("Windows")String os,@Optional("chrome")String browser) throws IOException
	{
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(browser.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser");
		return; //exit the execution if invalid browser is entered 
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//reading value from properties file
		driver.manage().window().maximize();	
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();		
	}
	
	public String randomstring()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomnumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	public String randomalphanumneric()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatedString+generatednumber;
	}
	
	public String capturescreen(String tname)
	{
		String timestamp = new SimpleDateFormat("yyyy:mm:dd:hh:ss").format(new Date());
		
		TakesScreenshot takesscreenshots = (TakesScreenshot)driver;
		File sourcefile= takesscreenshots.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + "timestamp" +".png";
		File targetfile = new File(targetfilepath);
		targetfile.renameTo(sourcefile);
		
		return targetfilepath;
		
	}

}
