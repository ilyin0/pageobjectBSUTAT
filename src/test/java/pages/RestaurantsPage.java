package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestaurantsPage extends AbstractPage {

    public static final String RESTAURANTS_PAGE_URL = "https://dominos.by/restaurants";

    @FindBy(xpath = "//div[@class=\"search-street__modal-content\"]//input[@class=\"custom-field-text__input\"]")
    private WebElement inputStreetName;

    @FindBy(xpath = "//input[..//div/text()=\"Номер дома\"]")
    private WebElement inputHouseNumber;

    @FindBy(xpath = "//div[./div/text()=\"Улица\"]")
    private WebElement divStreetInput;

    @FindBy(className = "store-locator__button")
    private WebElement btnCheck;

    @FindBy(className = "modal__close")
    private WebElement btnCloseAds;

    @FindBy(className = "notification")
    private WebElement divNotification;

    @FindBy(className = "notification__title")
    private WebElement divNotificationTitle;

    public RestaurantsPage(WebDriver driver) {
        super(driver);
    }

    public String getBtnCheckText() {
        waitTime(100);
        return btnCheck.getText();
    }

    public String getDivNotificationTitleText() {
        return divNotificationTitle.getText();
    }

    @Override
    public RestaurantsPage openPage() {
        driver.get(RESTAURANTS_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }

    public RestaurantsPage closeAds() {
        btnCloseAds.click();
        return this;
    }

    public RestaurantsPage checkDeliveryAddress(String cityName, String streetName, int houseNumber) {
        divStreetInput.click();
        WebElement searchInput = driver.findElement(By.xpath("//input[../div/text()=\"Поиск\"]"));
        searchInput.sendKeys(streetName);

        WebElement choiceBtn = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(d -> driver
                        .findElement(By.xpath("//button[./div/text()=\"" + streetName + "\" and ./div/text()=\"" + cityName + "\"]")));
        choiceBtn.click();

        inputHouseNumber.sendKeys(Integer.toString(houseNumber));

        btnCheck.click();

        return this;
    }


}
