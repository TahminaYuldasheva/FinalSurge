package tests;

import dto.Workout;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static pages.AddWorkoutModal.UPDATE_WORKOUT_TEXT;
import static pages.WorkoutDetailsPage.WORKOUT_DETAILS_TEXT;

public class AddNewWorkoutModalTest extends BaseTest {

    final String expectedOverallPlace = "15";
    final String expectedAgeGroupPlace = "3";

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
        softAssert.assertEquals((WORKOUT_DETAILS_TEXT), "Workout Details",
                "Workout Details page did not open!");

        workoutDetailsPage.clickUpdateButton();
        addWorkoutModal.isUpdateWorkoutModalOpened();
        softAssert.assertEquals((UPDATE_WORKOUT_TEXT),
                "Update Workout",
                "Unable to click the 'Update' button");
        workout.setMarkAsRace(true);
        workout.setOverallPlace(expectedOverallPlace);
        workout.setAgeGroupPlace(expectedAgeGroupPlace);
        addWorkoutModal.updateWorkoutRaceInfo(workout)
                .clickUpdateButton();

        softAssert.assertEquals(workout.getOverallPlace(),
                expectedOverallPlace,
                "Incorrect data!");
        softAssert.assertEquals(workout.getAgeGroupPlace(),
                expectedAgeGroupPlace,
                "Incorrect data!");
        softAssert.assertAll();
        softAssert.assertEquals((WORKOUT_DETAILS_TEXT), "Workout Details",
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
        assertEquals(trainingCalendarPage.getWorkoutInCalendarText(workout.getWorkoutName()), expected,
                "Training not found in the calendar!");
    }
}
