package main.java.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseUtil {

    WebDriver driver;

    public BaseUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void click(WebElement we) throws Exception {
        int counter = 20;
        while (counter >= 0) {
            try {
                if (we != null) {
                    we.click();
                    break;
                }
            } catch (Exception ex) {
                if (counter == 0) {
                    throw ex;
                }
                waitFor(500);
                counter--;
            }
        }
    }
}