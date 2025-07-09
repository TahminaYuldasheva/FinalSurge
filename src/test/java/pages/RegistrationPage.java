package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import dto.Account;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.DropDown;
import wrappers.Input;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class RegistrationPage extends BasePage {
    public static final By CREATE_NEW_ACCOUNT_BUTTON = By.xpath("//button[text()='Create New Account']");
    public static final By ERROR_TEXT = By.cssSelector("div.alert.alert-error");
    public static final By ERROR_TEXT_EMPTY = By.xpath("//label[@class='error']");

    @Step("Opening the registration page")
    public RegistrationPage openPage() {
        log.info("Opening the registration page");
        open("register.cshtml");
        return this;
    }

    @Step("Checking that the registration page is open")
    public RegistrationPage isPageOpened() {
        log.info("Checking that the registration page is open");
        $(CREATE_NEW_ACCOUNT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    public String getErrorEmptyFieldText() {
        return $(RegistrationPage.ERROR_TEXT_EMPTY).getText();
    }

    @Step("Fill out the registration form")
    public RegistrationPage createAccount(Account account) {
        log.info("Fill out the registration form to create an account {}", account.getEmailAddress());
        new Input("First Name").write(account.getFirstName());
        new Input("Last Name").write(account.getLastName());
        new Input("Email Address").write(account.getEmailAddress());
        new DropDown("Time Zone").select(account.getTimeZone());
        new Input("Password").write(account.getPassword());
        new Input("Re-type password").write(account.getReTypePassword());
        return this;
    }

    @Step("Fill out the registration form without 'Last Name' field")
    public RegistrationPage createAccountWithoutLastName(Account account) {
        log.info("Fill out the registration form without 'Last Name' field");
        new Input("First Name").write(account.getFirstName());
        new Input("Email Address").write(account.getEmailAddress());
        new DropDown("Time Zone").select(account.getTimeZone());
        new Input("Password").write(account.getPassword());
        new Input("Re-type password").write(account.getReTypePassword());
        return this;
    }

    @Step("Click on the 'Create New Account' button")
    public void clickCreateNewAccountButton() {
        $(CREATE_NEW_ACCOUNT_BUTTON).click();
    }

    @Step("Сhecking that there is no error while creating an account")
    public void assertNoErrorMessageVisible() {
        log.info("Сhecking that there is no error while creating an account");
        ElementsCollection errors = $$(ERROR_TEXT);
        if (!errors.isEmpty()) {
            errors.get(0).shouldNotBe(Condition.visible);
        }
    }
}
