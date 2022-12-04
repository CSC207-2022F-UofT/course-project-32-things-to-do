package use_cases.course_tracker.grade_calculator_use_case;

import java.util.ArrayList;

/**
 * Input data object for the Grade Calculator use case
 * Located in the use case layer
 */
public class GradeCalculatorRequestModel {

    private final String courseName;
    private final String userInput;
    private final ArrayList<String> ungradedTasks;

    public GradeCalculatorRequestModel(String courseName, String userInput, ArrayList<String> ungradedTasks) {
        this.courseName = courseName;
        this.userInput = userInput;
        this.ungradedTasks = ungradedTasks;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getUserInput() {
        return userInput;
    }

    public ArrayList<String> getUngradedTasks() {
        return ungradedTasks;
    }
}
