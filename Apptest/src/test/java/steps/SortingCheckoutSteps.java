package steps;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;
import static org.junit.Assert.*;

public class SortingCheckoutSteps {
    AndroidDriver<MobileElement> driver;

    @Given("User is on homepage")
    public void user_is_on_homepage() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app", "/path/to/your/app.apk");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.findElement(By.id("homepage_id")).isDisplayed();
    }

    @When("User sorts items by price from low to high")
    public void user_sorts_items_by_price_from_low_to_high() {
        driver.findElement(By.id("sortDropdown")).click();
        driver.findElement(By.id("lowToHighOption")).click();
    }

    @Then("The first item price should be less than the second item price")
    public void the_first_item_price_should_be_less_than_the_second_item_price() {
        List<MobileElement> prices = driver.findElements(By.className("itemPrice"));
        double firstPrice = Double.parseDouble(prices.get(0).getText().replace("$", ""));
        double secondPrice = Double.parseDouble(prices.get(1).getText().replace("$", ""));
        assertTrue(firstPrice < secondPrice);
        driver.quit();
    }

    @When("User adds 2 different items to cart")
    public void user_adds_2_different_items_to_cart() {
        driver.findElement(By.id("item1")).click();
        driver.findElement(By.id("addToCartButton")).click();
        driver.findElement(By.id("item2")).click();
        driver.findElement(By.id("addToCartButton")).click();
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        driver.findElement(By.id("cart")).click();
        driver.findElement(By.id("checkoutButton")).click();
    }

    @Then("User should see the success checkout page")
    public void user_should_see_the_success_checkout_page() {
        assertTrue(driver.findElement(By.id("successMessage")).isDisplayed());
        driver.quit();
    }
}
