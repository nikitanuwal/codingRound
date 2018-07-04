package tests;

import com.sun.javafx.PlatformUtil;
import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.HotelBooking;

public class HotelBookingTest {

    WebDriver driver = BrowserFactory.getBrowser("Chrome");;

    @Test
    public void shouldBeAbleToSearchForHotels() {

        driver.get("https://www.cleartrip.com/");
        HotelBooking HotelPage = PageFactory.initElements(driver, HotelBooking.class);
        HotelPage.hotelLink.click();

        HotelPage.localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(HotelPage.travellerSelection).selectByVisibleText("1 room, 2 adults");
        HotelPage.searchButton.click();
    }
}
