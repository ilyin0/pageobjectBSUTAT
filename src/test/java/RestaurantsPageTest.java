import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.RestaurantsPage;

public class RestaurantsPageTest {

    RestaurantsPage page;

    @BeforeTest
    public void setupDriverAndBrowserAndSite(){
        page = new RestaurantsPage(new ChromeDriver());
    }

    @AfterTest
    public void quitBrowser() {
        page.closePage();
    }

    @Test
    public void testIsAvailableDeliveryAddress() {
        SoftAssert softAssertion = new SoftAssert();

        page.openPage().closeAds();

        String cityName  = "МИНСК";
        String streetName = "УМАНСКАЯ УЛ.";
        int houseNumber = 37;

        softAssertion.assertEquals(page.checkDeliveryAddress(cityName, streetName, houseNumber).getBtnCheckText(), "Адрес в зоне доставки", "Button text has NOT been changed");
        Assert.assertEquals(page.getDivNotificationTitleText(), "Вы находитесь в зоне доставки", "There is notification delivery is NOT available");
    }
}
