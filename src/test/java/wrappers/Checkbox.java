package wrappers;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Checkbox {

    public static final String ADD_WORKOUT_MODAL_LOCATOR = "//span[text()='%s']/..//input";
    public static final String LOCATOR_WORKOUT = "//label[text()='%s']/..//div//label//span[text()='%s']/..//input";

    String label;

    public Checkbox(String label) {
        this.label = label;
    }

    @Step("Click on '{this.label}'")
    public void select() {
        log.info("Click on {}", "'" + label + "'");
        $x(String.format(ADD_WORKOUT_MODAL_LOCATOR, label)).click();
    }

    @Step("Selecting '{option}' in '{this.label}'")
    public void selectOption(String option) {
        if (option != null && !option.isEmpty()) {
            log.info("Click on {} in {}", "'" + option + "'", "'" + label + "'");
            $(By.xpath(String.format(LOCATOR_WORKOUT, label, option))).
                    click();
        }
    }
}


