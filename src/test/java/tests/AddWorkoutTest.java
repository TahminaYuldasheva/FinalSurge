package tests;

import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import pages.AddWorkoutModal;

import static org.testng.Assert.assertEquals;

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
        Selenide.sleep(1000);
        assertEquals(AddWorkoutModal.ADD_NEW_WORKOUT_MESSAGE, "Add New Workout",
                "Add Workout modal did not open!");
    }
}
