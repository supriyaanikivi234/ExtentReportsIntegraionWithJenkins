package Mnaager;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class WebsiteALoginTest extends ExtentTestListener {
	static WebDriver driver;
	
	@BeforeSuite
	public void setup() 
	{

		if(System.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("opened chrome browser");
			
		} else if(System.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("opened firefox browser");
			
		} else if(System.getProperty("browser").equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("opened edge browser");
		}
		
		driver.manage().window().maximize();
		
		Capabilities cap= ((RemoteWebDriver)driver).getCapabilities();
			extent.setSystemInfo("browserName", cap.getBrowserName());
			 extent.setSystemInfo("BrowserVersion", cap.getBrowserVersion());
	}
	  
    @Test
    public void testCase1() {
        
        Assert.assertTrue(true);
        test.assignAuthor("supriya");
	      test.assignCategory("smoke testcases");
	      test.assignDevice("chrome 99");
    }
    
    @Test
    public void testCase2() {
    
    	driver.get("URL");
    	test.info("Navigated to Url");
		driver.findElement(By.name("q")).sendKeys("HYR Tutorials", Keys.ENTER);
		test.info("Entered text in search box");
		String expectedTitle = "HYR Tutorials - Google Search";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle, "Title is mismatched");
		test.pass("Assertion is passed for webpage title");
	      test.assignAuthor("megha");
	      test.assignCategory("regrssion testcases");
	      test.assignDevice("chrome 89");

    }
    
    @Test
    public void testCase3() {
    
        Assert.assertTrue(true);
        test.assignAuthor("supriya");
	      test.assignCategory("smoke testcases");
	      test.assignDevice("chrome 99");
    }
     
    @Test
    public void testCase4() throws InterruptedException {
    	    	  driver.get("URL");
    	  Thread.sleep(1000);

    	  test.fail("Navigate to google .com");
    	   test.assignAuthor("megha");
 	      test.assignCategory("regrssion testcases");
 	      test.assignDevice("chrome 89");
        Assert.assertTrue(false);
        
    }
     
    @Test
    public void testCase5() throws InterruptedException {
    	  driver.get("URL");
     	  Thread.sleep(1000);

        test.skip("Navigate to google.com");
        test.assignAuthor("supriya");
	      test.assignCategory("smoke testcases");
	      test.assignDevice("chrome 99");
        throw new SkipException("Skipping this test with exception");
        
    }
    
    @Test(enabled=false)
	public void testCase6(){
			test = extent.createTest("Test Case 6", "I'm Not Ready, please don't execute me");
		}
    

    
    @AfterSuite
	public void teardown() {
		driver.quit();
	}
    
    
    public static String getScreenshot()
    {
    	   Date currentDate= new Date();
           String screenshotFile= currentDate.toString().replace(" ", "-").replace(":", "-");
           File f1= new File("C:\\\\Users\\\\DELL\\\\eclipse-workspace\\\\ExtentReportsIntegraionWithJenkins\\secrenshot\\"+screenshotFile+".png");		
           File scrShot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           try {
   			FileUtils.copyFile(scrShot, f1);
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} 
          String path=f1.getAbsolutePath();
    	   return path;
    }

}





