package use_cases.course_tracker.progress_tracker_use_case;

/**
 * Request Model for the Progress Tracker Use Case
 * Acts as the input data object in the use case layer
 */

public class ProgressTrackerRequestModel {

    private final String courseName;

    private final String newGradeTaskName;

    private final String newGrade; //-1 for empty input

    private final String newGoalGrade; //-1 for empty input

    public ProgressTrackerRequestModel(String courseName, String newGradeTaskName, String newGrade,
                                       String newGoalGrade) {
        this.courseName = courseName;
        this.newGradeTaskName = newGradeTaskName;
        this.newGrade = newGrade;
        this.newGoalGrade = newGoalGrade;
    }

    public String getcourseName() {
        return courseName;
    }

    public String getNewGradeTaskName() {
        return newGradeTaskName;
    }

    public String getNewGrade() {
        return newGrade;
    }

    public String getNewGoalGrade() {
        return newGoalGrade;
    }

}
