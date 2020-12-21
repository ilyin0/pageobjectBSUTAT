package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {

    public static final String MAIN_PAGE_URL = "https://dominos.by";

    @FindBy(className = "authorization-cta")
    private WebElement divAuthorizationAndProfileButton;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[@type=\"submit\" and text()=\"Войти\"]")
    private WebElement btnLogin;

    @FindBy(xpath = "//a[text()=\"Профиль\"]")
    private WebElement aProfile;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage login(String email, String password) {
        divAuthorizationAndProfileButton.click();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        btnLogin.click();
        return this;
    }

    public ProfilePage clickAndGoToProfile() {
        waitTime(500);
        aProfile.click();
        return new ProfilePage(driver);
    }

    @Override
    public MainPage openPage() {
        driver.get(MAIN_PAGE_URL);
        waitJavascriptIsExecuted(WAIT_TIMEOUT_SECONDS);
        return this;
    }
}
