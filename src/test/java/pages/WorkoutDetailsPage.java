package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class WorkoutDetailsPage extends BasePage {
    public static final String WORKOUT_DETAILS_TEXT = "Workout Details";
    public static final String UPDATE_BUTTON = "//a[@id='WorkoutEditLink']//span";

    @Step("Opening the 'Workout Details' page")
    public WorkoutDetailsPage openPage() {
        log.info("Opening the 'Workout Details' page");
        open("WorkoutDetails.cshtml");
        return this;
    }

    @Step("Checking that the 'Workout Details' page is open")
    public WorkoutDetailsPage isPageOpened() {
        log.info("Checking that the 'Workout Details' page is open");
        $(WORKOUT_DETAILS_TEXT).shouldBe(Condition.visible);
        return this;
    }

    @Step("Clicking on the 'Update' button to go to the 'Update Workout' modal")
    public void clickUpdateButton() {
        log.info("Clicking on the 'Update' button to go to the 'Update Workout' modal");
        $x(UPDATE_BUTTON).click();
    }
}
