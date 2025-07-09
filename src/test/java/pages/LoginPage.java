package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class LoginPage extends BasePage {

    public static final By LOGIN_BUTTON = By.xpath("//button[text()='Login']");
    public static final By EMAIL_FIELD = By.id("login_name");
    public static final By PASSWORD_FIELD = By.id("login_password");
    public static final By ERROR_TEXT_EMPTY = By.xpath("//label[@class='error']");
    public static final By ERROR_TEXT = By.cssSelector("div.alert.alert-error");

    @Step("Opening the login page")
    public LoginPage openPage() {
        log.info("Opening the login page");
        open("login.cshtml");
        return this;
    }

    @Step("Checking that the login page is open")
    public LoginPage isPageOpened() {
        log.info("Checking that the login page is open");
        $(LOGIN_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public String getErrorText() {
        return $(ERROR_TEXT).getText();
    }

    public String getErrorEmptyText() {
        return $(ERROR_TEXT_EMPTY).getText();
    }

    @Step("Log in with username '{user}' and password '{password}'")
    public LoginPage login(String user, String password) {
        log.info("Log in with username {} and password {}", user, password);
        $(EMAIL_FIELD).setValue(user);
        $(PASSWORD_FIELD).setValue(password);
        $(LOGIN_BUTTON).submit();
        return this;
    }

    @Step("Checking that no error message is visible on the page")
    public void assertNoErrorMessageVisible() {
        log.info("Checking that no error message is visible on the page");
        ElementsCollection errors = $$(ERROR_TEXT);
        if (!errors.isEmpty()) {
            errors.get(0).shouldNotBe(Condition.visible);
        }
    }
}
