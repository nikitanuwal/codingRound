package framework;

import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

    /*
     * Factory method for getting browsers
     */
    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
            case "Firefox":
                driver = drivers.get("Firefox");
                if (driver == null) {
                    driver = new FirefoxDriver();
                    drivers.put("Firefox", driver);
                }
                break;

            case "Chrome":
                driver = drivers.get("Chrome");
                if (driver == null) {
                    setDriverPath();
                    ChromeOptions ops = new ChromeOptions();
                    ops.addArguments("--disable-notifications");
                    ops.addArguments("--disable-infobars");
                    driver = new ChromeDriver(ops);
                    driver.manage().window().maximize();
                    drivers.put("Chrome", driver);
                }
                break;
        }
        return driver;
    }

    @AfterMethod(alwaysRun = true)
    public static void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }

    public static void setDriverPath() {
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