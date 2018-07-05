package main.java.pages;

import main.java.framework.BaseUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HotelBooking extends BaseUtil {

    WebDriver driver;

    @FindBy(xpath = "//*[@title='Find hotels in destinations around the world']")
    public WebElement hotelLink;

    @FindBy(id = "Tags")
    public WebElement localityTextBox;
    
    @FindBy(id = "CheckInDate")
    public WebElement checkInDate;
    
    @FindBy(id = "CheckOutDate")
    public WebElement checkOutDate;

    @FindBy(id = "SearchHotelsButton")
    public WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    public WebElement travellerSelection;

    public HotelBooking(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSearchButton() throws Exception {
        click(searchButton);
    }
}