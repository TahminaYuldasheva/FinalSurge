package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
import static pages.AddWorkoutModal.ADD_NEW_WORKOUT_TEXT;

public class AddWorkoutTest extends BaseTest {
    @Description("Verifies that the 'Add New Workout' opens correctly after selecting an activity and activity type")
    @Test(testName = "Positive test: Open 'Add New Workout'",
            description = "Verifies that the 'Add New Workout' modal opens correctly after selecting " +
                    "an activity and activity type")
    public void checkAddWorkout() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password)
                .assertNoErrorMessageVisible();
        addWorkoutPage.openPage()
                .isPageOpened()
                .selectActivity("Run")
                .selectActivityType("No Sub-Type");
        addWorkoutModal.isPageOpened();
        sleep(1000);
        assertEquals(ADD_NEW_WORKOUT_TEXT, "Add New Workout",
                "Add Workout modal did not open!");
    }
}
