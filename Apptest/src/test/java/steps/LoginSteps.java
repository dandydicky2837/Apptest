package steps;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import static org.junit.Assert.*;

public class LoginSteps {
    AndroidDriver<MobileElement> driver;

    @Given("User is on login page")
    public void user_is_on_login_page() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app", "/path/to/your/app.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.findElement(By.id("login_page_id")).isDisplayed();
    }

    @When("User enters valid username and password")
    public void user_enters_valid_username_and_password() {
        driver.findElement(By.id("username")).sendKeys("valid_username");
        driver.findElement(By.id("password")).sendKeys("valid_password");
    }

    @When("User enters invalid username or password")
    public void user_enters_invalid_username_or_password() {
        driver.findElement(By.id("username")).sendKeys("invalid_username");
        driver.findElement(By.id("password")).sendKeys("invalid_password");
    }

    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.id("loginButton")).click();
    }

    @Then("User should be redirected to homepage")
    public void user_should_be_redirected_to_homepage() {
        assertTrue(driver.findElement(By.id("homepage_id")).isDisplayed());
    }

    @Then("User should see the items listed")
    public void user_should_see_the_items_listed() {
        assertTrue(driver.findElement(By.id("itemsList")).isDisplayed());
        driver.quit();
    }

    @Then("Error message {string} should be displayed")
    public void error_message_should_be_displayed(String errorMessage) {
        String actualMessage = driver.findElement(By.id("errorMessage")).getText();
        assertEquals(errorMessage, actualMessage);
        driver.quit();
    }
}
