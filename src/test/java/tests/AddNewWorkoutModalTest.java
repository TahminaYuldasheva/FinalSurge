package tests;

import dto.Workout;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddWorkoutModal;
import pages.TrainingCalendarPage;
import pages.WorkoutDetailsPage;

import static com.codeborne.selenide.Selenide.$x;
import static org.testng.Assert.assertEquals;

public class AddNewWorkoutModalTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    Workout workout;

    @Description("Create a workout and successfully update it with race-specific information")
    @Test(testName = "Positive test: Create and update Workout with 'Race Data'",
            description = "Create a workout and successfully update it with race-specific information")
    public void checkCreateAndChangeWorkout() {
        workout = Workout.builder()
                .date("06/27/2025")
                .timeOfDay("06:30 AM")
                .workoutName("Everyday running")
                .workoutDescription("Morning interval session")
                .showPlannedDistance(false)
                .distance("10")
                .distanceUnit("mi")
                .duration("01:10:00")
                .pace("7")
                .timeUnit("min/mi")
                .howIFelt("Good")
                .perceivedEffort("4 (Moderate)")
                .minHR("80")
                .avgHR("110")
                .maxHR("140")
                .caloriesBurned("750")
                .build();

        loginPage.openPage()
                .isPageOpened()
                .login(user, password)
                .assertNoErrorMessageVisible();
        addWorkoutPage.openPage()
                .isPageOpened()
                .selectActivity("Run")
                .selectActivityType("No Sub-Type");
        addWorkoutModal.isPageOpened()
                .createWorkoutModal(workout)
                .clickAddWorkoutButton();
        addWorkoutModal.checkNoErrorMessageVisible();
        softAssert.assertEquals((WorkoutDetailsPage.WORKOUT_DETAILS_TEXT), "Workout Details",
                "Workout Details page did not open!");

        workoutDetailsPage.clickUpdateButton();
        addWorkoutModal.isUpdateWorkoutModalOpened();
        assertEquals((AddWorkoutModal.UPDATE_WORKOUT_MESSAGE),
                "Update Workout",
                "Unable to click the 'Update' button");
        workout.setMarkAsRace(true);
        workout.setOverallPlace("15");
        workout.setAgeGroupPlace("3");
        addWorkoutModal.updateWorkoutRaceInfo(workout)
                .clickUpdateButton();

        softAssert.assertEquals(workout.getOverallPlace(),
                "15",
                "Incorrect data!");
        softAssert.assertEquals(workout.getAgeGroupPlace(),
                "3",
                "Incorrect data!");
        softAssert.assertAll();
        softAssert.assertEquals((WorkoutDetailsPage.WORKOUT_DETAILS_TEXT), "Workout Details",
                "Workout Details page did not open!");
    }

    @Description("Checking that the newly created workout appears correctly in the training calendar after being added")
    @Test(testName = "Positive test: Verify Workout is Displayed in Calendar",
            description = "Checking that the newly created workout appears correctly in the training calendar",
            dependsOnMethods = "checkCreateAndChangeWorkout")
    public void checkAddedWorkoutInCalendar() {
        loginPage.openPage()
                .isPageOpened()
                .login(user, password)
                .assertNoErrorMessageVisible();
        trainingCalendarPage.openPage()
                .isPageOpened()
                .checkWorkoutIsDisplayedInCalendar(workout.getWorkoutName());
        String expected = "Run: " + workout.getWorkoutName();
        assertEquals($x(String.format(TrainingCalendarPage.WORKOUT_IN_CALENDAR, expected)).getText(), expected,
                "Training not found in the calendar!");
    }
}
