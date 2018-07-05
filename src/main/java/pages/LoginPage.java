package main.java.pages;

import main.java.framework.BaseUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseUtil {

    WebDriver driver;

    @FindBy(linkText = "Your trips")
    public WebElement yourTripLink;

    @FindBy(id = "SignIn")
    public WebElement signIn;

    @FindBy(id = "signInButton")
    public WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}