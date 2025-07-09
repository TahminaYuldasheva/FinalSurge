package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class AddWorkoutPage extends BasePage {

    @Step("Opening the 'Add Workout' page")
    public AddWorkoutPage openPage() {
        log.info("Opening the 'Add Workout' page");
        open("WorkoutAdd.cshtml");
        return this;
    }

    @Step("Checking that the 'Add Workout' page is open")
    public AddWorkoutPage isPageOpened() {
        log.info("Checking that the 'Add Workout' page is open");
        $(byText("Select an Activity Type")).shouldBe(Condition.visible);
        return this;
    }

    @Step("Selecting an activity on the 'Add Workout' page")
    public AddWorkoutPage selectActivity(String activity) {
        log.info("Selecting {} on the 'Add Workout' page", "'" + activity + "'");
        $(byText(activity)).click();
        return new AddWorkoutPage();
    }

    @Step("Selecting activity type on 'Add Workout' page")
    public AddWorkoutPage selectActivityType(String activityType) {
        log.info("Selecting {} on 'Add Workout' page", "'" + activityType + "'");
        $(byText(activityType)).click();
        return this;
    }
}
