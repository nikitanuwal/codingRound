package main.java.tests;

import main.java.framework.BrowserFactory;
import main.java.pages.FlightBooking;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends BrowserFactory {
    FlightBooking FlightPage;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        FlightPage = new FlightBooking(driver);
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        driver.get("https://www.cleartrip.com/");
        FlightPage.oneWay.click();
        FlightPage.origin.clear();
        FlightPage.origin.sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin

        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        FlightPage.destination.clear();
        FlightPage.destination.sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination

        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        FlightPage.searchButton.click();

        //verify that result appears for the provided journey search
        Assert.assertTrue(FlightPage.isElementPresent(By.className("searchSummary")));
    }
}