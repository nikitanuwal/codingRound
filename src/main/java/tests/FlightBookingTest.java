package tests;

import framework.BaseUtil;
import com.sun.javafx.PlatformUtil;
import framework.BrowserFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlightBooking;

import java.util.List;

import static framework.BaseUtil.isElementPresent;
import static framework.BaseUtil.waitFor;

public class FlightBookingTest {

    WebDriver driver = BrowserFactory.getBrowser("Chrome");

    @Test
    public void testThatResultsAppearForAOneWayJourney() {

        FlightBooking FlightPage = PageFactory.initElements(driver, FlightBooking.class);
        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        FlightPage.oneWay.click();
        FlightPage.origin.clear();
        FlightPage.origin.sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin

        waitFor(2000);
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        FlightPage.destination.clear();
        FlightPage.destination.sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination

        waitFor(2000);
        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        FlightPage.searchButton.click();
        waitFor(5000);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary")));
    }
}
