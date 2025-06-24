package wrappers;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class DropDown {
    public static final String REGISTRATION_PAGE_LOCATOR = "//label[text()='%s']/ancestor::td//select";
    public static final String LOCATOR_WORKOUT_MODAL = "//label[text()='%s']//../following-sibling::select";

    String label;

    public DropDown(String label) {
        this.label = label;
    }

    @Step("Selecting '{option}' from '{this.label}'")
    public void select(String option) {
        log.info("Selecting {} from {}", "'" + option + "'", "'" + label + "'");
        $x(String.format(REGISTRATION_PAGE_LOCATOR, label)).click();
        $(byText(option)).click();
    }

    @Step("Selecting '{option}' from '{this.label}'")
    public void selectForAddWorkoutModal(String option) {
        if (option != null && !option.isEmpty()) {
            log.info("Selecting {} from {}", "'" + option + "'", "'" + label + "'");
            $(By.xpath(String.format(LOCATOR_WORKOUT_MODAL, label))).
                    selectOption(option);
        }
    }
}
