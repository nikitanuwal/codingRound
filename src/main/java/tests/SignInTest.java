package tests;

import framework.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import static framework.BaseUtil.waitFor;

public class SignInTest {

    WebDriver driver = BrowserFactory.getBrowser("Chrome");

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);
        LoginPage login = PageFactory.initElements(driver, LoginPage.class);
        login.yourTripLink.click();
        login.signIn.click();

        driver.switchTo().frame("modal_window");
        login.signInButton.click();
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
    }
}