package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightBooking {
    WebDriver driver;

    @FindBy(id = "OneWay")
    public WebElement oneWay;

    @FindBy(name = "origin")
    public WebElement origin;

    @FindBy(name = "destination")
    public WebElement destination;

    @FindBy(id = "SearchBtn")
    public WebElement searchButton;

    public FlightBooking(WebDriver driver){
        this.driver = driver;
    }
}
