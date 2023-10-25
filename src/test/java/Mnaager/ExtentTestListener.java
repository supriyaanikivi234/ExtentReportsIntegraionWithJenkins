package Mnaager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {
	  
	   public static WebDriver driver = null;
	    public static ExtentTest test;
	    public static ExtentSparkReporter htmlReporter;
	    public static ExtentReports extent;
  
    @Override
    public void onStart(ITestContext context) {
    	ExtentSparkReporter  htmlReporter = new ExtentSparkReporter("C:\\Users\\DELL\\eclipse-workspace\\ExtentReportsIntegraionWithJenkins\\ReportsHTML\\extentReport.html");
        
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        
        try {
			htmlReporter.loadXMLConfig(new File("./configuration/extent-reports-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
extent.attachReporter(htmlReporter);
         
        //To add system or environment info by using the setSystemInfo method.
       
 
	      extent.setSystemInfo("java.version", System.getProperty("java.version"));
	   
	      extent.setSystemInfo("URl","https://www.googe.com/");
	      extent.setSystemInfo("Username","admin123");
	      extent.setSystemInfo("password","mapple21234");

    }
   
    
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getName()+"Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	String path;
		try {
		path= WebsiteALoginTest.getScreenshot();
		test.addScreenCaptureFromPath(path,"googleHomePage");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
        test.fail(result.getThrowable());
        test.log(Status.FAIL, result.getName()+"Failed");
 
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getName()+"skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
    	 try {
             Desktop.getDesktop().browse(
                     new File("C:\\Users\\DELL\\eclipse-workspace\\ExtentReportsIntegraionWithJenkins\\ReportsHTML\\extentReport.html").toURI());
         } catch (IOException e) {
             e.printStackTrace();
         }
        extent.flush();
    }
}