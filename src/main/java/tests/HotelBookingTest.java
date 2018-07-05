package main.java.tests;

import main.java.framework.BrowserFactory;
import main.java.pages.HotelBooking;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HotelBookingTest extends BrowserFactory {

    HotelBooking hotelBooking;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        hotelBooking = new HotelBooking(driver);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() throws Exception {
        driver.get("https://www.cleartrip.com/");
        hotelBooking.hotelLink.click();
        hotelBooking.localityTextBox.sendKeys("Indiranagar, Bangalore");
        List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.xpath(".//li[@class='list' and @role='presentation']"));
        originOptions.get(0).click();
        hotelBooking.checkInDate.sendKeys(Keys.TAB);
        hotelBooking.checkOutDate.sendKeys(Keys.TAB);
        new Select(hotelBooking.travellerSelection).selectByVisibleText("1 room, 2 adults");
        hotelBooking.clickSearchButton();
        Assert.assertTrue(hotelBooking.isElementPresent(By.className("searchSummary")));
    }
}
