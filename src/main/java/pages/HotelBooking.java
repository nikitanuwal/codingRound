package pages;

import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by nikitanuwal on 7/4/2018.
 */
public class HotelBooking {

    WebDriver driver;

    @FindBy(xpath = "//*[@title='Find hotels in destinations around the world']")
    public WebElement hotelLink;

    @FindBy(xpath = "//*[@id='Tags']")
    public WebElement localityTextBox;

    @FindBy(xpath = "//*[@id='SearchHotelsButton']")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='travellersOnhome']")
    public WebElement travellerSelection;

    public HotelBooking(WebDriver driver){
        this.driver = driver;
    }
}
