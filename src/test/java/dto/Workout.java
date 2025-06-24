package dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Workout {
    String date;
    String timeOfDay;
    String workoutName;
    String workoutDescription;
    Boolean showPlannedDistance;
    String distance;
    String distanceUnit;
    String duration;
    String pace;
    String timeUnit;
    Boolean markAsRace;
    String overallPlace;
    String ageGroupPlace;
    String howIFelt;
    String perceivedEffort;
    String minHR;
    String avgHR;
    String maxHR;
    String caloriesBurned;
}
