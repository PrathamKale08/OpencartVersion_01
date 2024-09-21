package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.baseCases;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter esparkreporter;
	public ExtentReports ereports;
	public ExtentTest etests;
	String reportname;
	
	public void onStart(ITestContext testContext)
	{
		// Create the timestamp for the report name
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		reportname = "QA_report" + timestamp + ".html";
		
		// Create the reports directory if it doesn't exist
		File reportDir = new File("C:\\Users\\pratham.kale\\eclipse-workspace\\OpencartV_01\\reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs(); // Create the directory if it doesn't exist
        }
        
		// Set up the report location, title, and theme
		esparkreporter = new ExtentSparkReporter("C:\\Users\\pratham.kale\\eclipse-workspace\\OpencartV_01\\reports\\" + reportname);
		esparkreporter.config().setDocumentTitle("Pratham EQA Report");
		esparkreporter.config().setReportName("Opencart Functional Testing");
		esparkreporter.config().setTheme(Theme.DARK);
		
		// Initialize ExtentReports and attach the reporter
		ereports = new ExtentReports();
		ereports.attachReporter(esparkreporter);
		ereports.setSystemInfo("Application", "Opencart");
		ereports.setSystemInfo("Module", "Admin");
		ereports.setSystemInfo("Sub-module", "Customers");
		ereports.setSystemInfo("Username", System.getProperty("user.name"));
		ereports.setSystemInfo("Environment", "QA");

		// Set system info from test context
		String os = testContext.getCurrentXmlTest().getParameter("os");
		ereports.setSystemInfo("Operating System", os);
		 
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		ereports.setSystemInfo("Browser", browser);
		 
		List<String> IncludedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!IncludedGroups.isEmpty()) 
		{ 
			ereports.setSystemInfo("Included Groups", IncludedGroups.toString()); 
		}		
	}		
	
	public void onTestSuccess(ITestResult TestResult)
	{
		// Create test for each test method
		etests = ereports.createTest(TestResult.getMethod().getMethodName());
		if (TestResult.getMethod().getGroups().length > 0) 
		{
			etests.assignCategory(TestResult.getMethod().getGroups());
		}
		etests.log(Status.PASS, TestResult.getName() + " was successfully executed");	
	}
	
	public void onTestFail(ITestResult TestResult)
	{
		// Create test for each failed test method
		etests = ereports.createTest(TestResult.getMethod().getMethodName());
		if (TestResult.getMethod().getGroups().length > 0) 
		{
			etests.assignCategory(TestResult.getMethod().getGroups());
		}
		etests.log(Status.FAIL, TestResult.getName() + " has failed");
		etests.log(Status.INFO, TestResult.getThrowable().getMessage());
		
		// Capture screenshot on failure and add to report
		try {
			String image = new baseCases().capturescreen(TestResult.getName());
			etests.addScreenCaptureFromPath(image);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult TestResult)
	{
		// Create test for each skipped test method
		etests = ereports.createTest(TestResult.getMethod().getMethodName());
		if (TestResult.getMethod().getGroups().length > 0) {
			etests.assignCategory(TestResult.getMethod().getGroups());
		}
		etests.log(Status.SKIP, TestResult.getName() + " was skipped");
		etests.log(Status.INFO, TestResult.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context)
	{
		// Flush the report at the end of the test execution
		ereports.flush();
		
		// Open the report automatically in the default browser
		String pathofextentreport = System.getProperty("user.dir") + "\\reports\\" + reportname;
		File extentreport = new File(pathofextentreport);
		
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}