package pages;


import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class UserProfilePage extends BasePage {
    public static final By USER_PROFILE = By.xpath("//a[text()='Settings']");

    @Step("Checking that the 'User Profile' page is open")
    public UserProfilePage isPageOpened() {
        log.info("Checking that the 'User Profile' page is open");
        $(USER_PROFILE).shouldBe(Condition.visible);
        return this;
    }

    public String getSettingsText() {
        return $(USER_PROFILE).getText();
    }
}
