package wrappers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class Input {

    public static final String REGISTRATION_PAGE_LOCATOR = "//label[text()='%s']/ancestor::td//input";
    public static final String ADD_WORKOUT_MODAL_LOCATOR = "//label[text()='%s']/following::input";
    public static final String ADD_BASIC_WORKOUT_MODAL_LOCATOR = ".//label[text()='%s']/following-sibling::input";

    String label;
    SelenideElement context;

    public Input(String label) {
        this.label = label;
        this.context = $("#tb1_a");
    }

    @Step("Writing '{text}' in '{this.label}'")
    public void write(String text) {
        log.info("Writing {} in the {} field", "'" + text + "'", "'" + label + "'");
        $x(String.format(REGISTRATION_PAGE_LOCATOR, label)).setValue(text);
    }

    @Step("Writing '{text}' in '{this.label}'")
    public void writeAddNewWorkout(String text) {
        log.info("Writing {} in the {} field", "'" + text + "'", "'" + label + "'");
        $x(String.format(ADD_WORKOUT_MODAL_LOCATOR, label)).setValue(text);
    }

    @Step("Writing '{text}' in '{this.label}'")
    public void writeAddNewWorkoutBasic(String text) {
        log.info("Writing {} in the {} field", "'" + text + "'", "'" + label + "'");
        context.$x(String.format(ADD_BASIC_WORKOUT_MODAL_LOCATOR, label)).setValue(text);
    }
}


