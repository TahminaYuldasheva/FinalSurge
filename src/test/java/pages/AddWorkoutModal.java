package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import dto.Workout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.Checkbox;
import wrappers.DropDown;
import wrappers.Input;
import wrappers.TextArea;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class AddWorkoutModal extends BasePage {

    public static final By ADD_WORKOUT_BUTTON = By.xpath("//div/input[@id='saveButton']");
    public static final String ADD_NEW_WORKOUT_TEXT = "Add New Workout";
    public static final By UPDATE_BUTTON = By.xpath("//input[@id='saveButton']");
    public static final By ERROR_TEXT = By.cssSelector("div.alert.alert-error");
    public static final String UPDATE_WORKOUT_TEXT = "Update Workout";

    @Step("Checking that the 'Add Workout' modal is open")
    public AddWorkoutModal isPageOpened() {
        log.info("Checking that the 'Add Workout' modal is open");
        $(byText(ADD_NEW_WORKOUT_TEXT)).shouldBe(Condition.visible);
        return this;
    }

    public AddWorkoutModal isUpdateWorkoutModalOpened() {
        $(byText(UPDATE_WORKOUT_TEXT)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Filling out the 'Add Workout' form")
    public AddWorkoutModal createWorkoutModal(Workout workout) {
        log.info("Filling out the 'Add Workout' form to create a workout: {}", workout.getWorkoutName());
        new Input("Date").writeAddNewWorkout(workout.getDate());
        new Input("Time of Day").writeAddNewWorkout(workout.getTimeOfDay());
        new Input("Workout Name").writeAddNewWorkout(workout.getWorkoutName());
        new TextArea("Workout Description").writeAddNewWorkout(workout.getWorkoutDescription());
        new Checkbox("Show Planned Distance/Duration");
        sleep(1000);
        new Input("Distance").writeAddNewWorkoutBasic(workout.getDistance());
        new DropDown("Distance").selectForAddWorkoutModal(workout.getDistanceUnit());
        new Input("Duration").writeAddNewWorkoutBasic(workout.getDuration());
        new Input("Pace").writeAddNewWorkoutBasic(workout.getPace());
        new DropDown("Pace").selectForAddWorkoutModal(workout.getTimeUnit());
        new Checkbox("Mark as Race");
        new Checkbox("How I Felt").selectOption(workout.getHowIFelt());
        new DropDown("Perceived Effort").selectForAddWorkoutModal(workout.getPerceivedEffort());
        new Input("Min HR").writeAddNewWorkout(workout.getMinHR());
        new Input("Avg HR").writeAddNewWorkout(workout.getAvgHR());
        new Input("Max HR").writeAddNewWorkout(workout.getMaxHR());
        new Input("Calories Burned").writeAddNewWorkout(workout.getCaloriesBurned());
        return this;
    }

    @Step("Making changes to a current workout")
    public AddWorkoutModal updateWorkoutRaceInfo(Workout workout) {
        log.info("Making changes to the {} workout", "'" + workout.getWorkoutName() + "'");
        new Checkbox("Mark as Race").select();
        new Input("Overall Place").writeAddNewWorkout(workout.getOverallPlace());
        new Input("Age Group Place").writeAddNewWorkout(workout.getAgeGroupPlace());
        return this;
    }

    @Step("Сlicking on the 'Add Workout' button")
    public void clickAddWorkoutButton() {
        log.info("Сlicking on the 'Add Workout' button");
        $(ADD_WORKOUT_BUTTON).click();
    }

    @Step("Сlicking on the 'Update' button to save changes to the current workout")
    public void clickUpdateButton() {
        log.info("Сlicking on the 'Update' button to save changes to the current workout");
        $(UPDATE_BUTTON).click();
    }

    @Step("Сhecking that there is no error while creating a workout")
    public void checkNoErrorMessageVisible() {
        log.info("Сhecking that there is no error while creating a workout");
        ElementsCollection errors = $$(ERROR_TEXT);
        if (!errors.isEmpty()) {
            errors.get(0).shouldNotBe(Condition.visible);
        }
    }
}
