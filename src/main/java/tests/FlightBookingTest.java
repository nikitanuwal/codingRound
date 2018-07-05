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
    FlightBooking flightPage;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        flightPage = new FlightBooking(driver);
    }

    @Test
    public void testThatResultsAppearForAOneWayJourney() {
        driver.get("https://www.cleartrip.com/");
        flightPage.oneWay.click();
        flightPage.origin.clear();
        flightPage.origin.sendKeys("Bangalore");
        //wait for the auto complete options to appear for the origin

        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
        originOptions.get(0).click();

        flightPage.destination.clear();
        flightPage.destination.sendKeys("Delhi");
        //wait for the auto complete options to appear for the destination

        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();

        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")).click();

        //all fields filled in. Now click on search
        flightPage.searchButton.click();

        //verify that result appears for the provided journey search
        Assert.assertTrue(flightPage.isElementPresent(By.className("searchSummary")));
    }
}