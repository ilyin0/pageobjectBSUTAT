import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class RestaurantsPageTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupDriverAndBrowserAndSite(){
        driver = new ChromeDriver();
        driver.get("https://dominos.by/restaurants");
        driver.manage().window().fullscreen();
        WebElement btnCloseAds = new WebDriverWait(driver, 5).until(d->driver.findElement(By.className("modal__close")));
        btnCloseAds.click();
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testIsAvailableDeliveryAddress() {
        WebElement form = driver.findElement(By.xpath("//form[@class=\"store-locator__form\"]"));

        WebElement streetInputDiv = form.findElement(By.xpath("//div[./div[1]/text()=\"Улица\"]"));
        streetInputDiv.click();

        driver.findElement(By.xpath("//div[@class=\"search-street__modal-content\"]//input[@class=\"custom-field-text__input\"]")).sendKeys("УМАНСКАЯ УЛ.");
        new WebDriverWait(driver, 5).until(d->driver.findElement(By.xpath("//li/button[./div[1]/text()=\"УМАНСКАЯ УЛ.\" and ./div[2]/text()=\"МИНСК\"]"))).click();

        WebElement houseNumberInputDiv = form.findElement(By.xpath("//div[./div[1]/text()=\"Номер дома\"]"));
        houseNumberInputDiv.findElement(By.tagName("input")).sendKeys("37");

        WebElement checkAvailabilityButton = driver.findElement(By.xpath("//form[@class=\"store-locator__form\"]/button"));
        checkAvailabilityButton.click();

        new WebDriverWait(driver, 2).until(d-> checkAvailabilityButton.getText().equals("Адрес в зоне доставки"));

        Assert.assertEquals(checkAvailabilityButton.getText(), "Адрес в зоне доставки", "Button didn't change it's text inside");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"notification\"]/div[@class=\"notification__title\"]")).getText(), "Вы находитесь в зоне доставки", "There is notification delivery is NOT available");

    }
}
