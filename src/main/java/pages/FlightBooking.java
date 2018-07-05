package main.java.pages;

import main.java.framework.BaseUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightBooking extends BaseUtil {
    WebDriver driver;

    @FindBy(id = "OneWay")
    public WebElement oneWay;

    @FindBy(name = "origin")
    public WebElement origin;

    @FindBy(name = "destination")
    public WebElement destination;

    @FindBy(id = "SearchBtn")
    public WebElement searchButton;

    public FlightBooking(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
