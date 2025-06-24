package wrappers;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$x;

@Log4j2
public class TextArea {

    public static String ADD_WORKOUT_MODAL_LOCATOR = "//label[text()='%s']/following::textarea[1]";
    String label;

    public TextArea(String label) {
        this.label = label;
    }

    @Step("Writing '{text}' in '{this.label}'")
    public void writeAddNewWorkout(String text) {
        log.info("Writing {} in the {} field", "'" + text + "'", "'" + label + "'");
        $x(String.format(ADD_WORKOUT_MODAL_LOCATOR, label)).setValue(text);
    }
}
