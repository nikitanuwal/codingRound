package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by nikitanuwal on 7/4/2018.
 */
public class LoginPage {

    WebDriver driver;

    @FindBy(linkText = "Your trips")
    public WebElement yourTripLink;

    @FindBy(id = "SignIn")
    public WebElement signIn;

    @FindBy(id = "signInButton")
    public WebElement signInButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
}
