package main.java.framework;

import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class BrowserFactory {
    protected WebDriver driver;
    private static int totalPassedTCs;
	private static int totalFailedTCs;
	private static int totalSkippedTCs;
	private Date g_StartTime;
	private Date g_EndTime;
	
	@BeforeSuite(alwaysRun = true)
	public void setup(final ITestContext testContext) throws IOException {
		g_StartTime = new Date();
		totalPassedTCs = 0;
		totalFailedTCs = 0;
		totalSkippedTCs = 0;
	}

    public WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName.trim().toUpperCase()) {
            case "CHROME":
                setDriverPath();
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--window-size=1600,1900");
                ops.addArguments("--disable-notifications");
                ops.addArguments("--disable-infobars");
                driver = new ChromeDriver(ops);
                break;
        }
        driver.manage().timeouts().implicitlyWait(36, TimeUnit.SECONDS);
        return driver;
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void initDriver(@Optional("chrome") String browser, Method method) {
    	System.out.println("########################" + method.getName() + " EXECUTION STARTED########################");
        this.driver = getBrowser(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver(Method method, ITestResult tr) {
    	if(tr.getStatus() == 1){
			System.out.println("########################" + method.getName() + " EXECUTION PASSED########################");
			totalPassedTCs += 1;
		} else if(tr.getStatus() == 3){
			System.out.println("########################" + method.getName() + " EXECUTION SKIPPED########################");
			totalSkippedTCs += 1;
		} else if(tr.getStatus() == 2){
			System.out.println("########################" + method.getName() + " EXECUTION FAILED########################");
			totalFailedTCs += 1;
		}
    	if(driver != null)
    		driver.quit();
    }
    
    @AfterSuite(alwaysRun=true)
	public void tearDown() {
    	g_EndTime = new Date();
		if(g_StartTime != null && g_EndTime != null) {
			String strTimeDifference = fnTimeDiffference(g_StartTime.getTime(), g_EndTime.getTime());
			System.out.println("Total suite execution time : " + strTimeDifference);
			System.out.println("Total passed test cases : " + totalPassedTCs);
			System.out.println("Total failed test cases : " + totalFailedTCs);
			System.out.println("Total skipped test cases : " + totalSkippedTCs);
		}
    }

    public void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }
    
    public String fnTimeDiffference(long startTime, long endTime) {
		long delta = endTime - startTime;
		int days = (int)delta / 86400000;
		delta = (int)delta % 86400000;
		int hrs = (int)delta / 3600000;
		delta = (int)delta % 3600000;
		int min = (int)delta / 60000;
		delta = (int)delta % 60000;
		int sec = (int)delta / 1000;
		
		String strTimeDifference = days + "d " + hrs + "h " + min + "m " + sec + "s";
		return strTimeDifference;
	}
}