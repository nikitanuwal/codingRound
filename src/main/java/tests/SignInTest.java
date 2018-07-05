package main.java.tests;

import main.java.framework.BrowserFactory;
import main.java.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BrowserFactory {
    LoginPage login;

    @BeforeMethod(alwaysRun = true)
    public void init() {
        login = new LoginPage(driver);
    }

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        driver.get("https://www.cleartrip.com/");
        login.yourTripLink.click();
        login.signIn.click();

        driver.switchTo().frame("modal_window");
        login.signInButton.click();
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
    }
}