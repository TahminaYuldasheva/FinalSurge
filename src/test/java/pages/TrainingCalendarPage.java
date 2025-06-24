package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TrainingCalendarPage extends BasePage {
    public static final By TRAINING_CALENDAR_TEXT = By.xpath("//a[text()='Training Calendar']");
    public static final String WORKOUT_IN_CALENDAR = "//td//div[contains(text(), '%s')]";

    @Step("Opening the 'Training Calendar' page")
    public TrainingCalendarPage openPage() {
        log.info("Opening the 'Training Calendar' page");
        open("Calendar.cshtml");
        return this;
    }

    @Step("Checking that the 'Training Calendar' page is open")
    public TrainingCalendarPage isPageOpened() {
        log.info("Checking that the 'Training Calendar' page is open");
        $(TRAINING_CALENDAR_TEXT).shouldBe(Condition.visible);
        return this;
    }

    @Step("Checking that the created workout is displayed in the calendar")
    public TrainingCalendarPage checkWorkoutIsDisplayedInCalendar(String workoutName) {
        log.info("Checking that the {} workout is displayed in the calendar",
                "'" + workoutName + "'");
        String fullTitle = "Run: " + workoutName;
        String xpath = String.format(WORKOUT_IN_CALENDAR, fullTitle);
        $x(xpath).shouldBe(Condition.visible);
        return this;
    }
}
