package main.java.framework;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public abstract class BrowserFactory {
    protected WebDriver driver;

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
    public void initDriver(@Optional("chrome") String browser) {
        this.driver = getBrowser(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
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
}