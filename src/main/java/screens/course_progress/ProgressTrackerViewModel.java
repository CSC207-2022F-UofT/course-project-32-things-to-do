package screens.course_progress;

import java.util.ArrayList;

/**
 * Data object for presenter to view dependency inversion
 * ViewModel is located in the controller/presenter layer
 */

public class ProgressTrackerViewModel {
    private final String displayString;

    private final ArrayList<String> ungradedTasks;

    private final double mockGrade;

    public ProgressTrackerViewModel(String displayString, ArrayList<String> ungradedTasks, double mockGrade) {
        this.displayString = displayString;
        this.ungradedTasks = ungradedTasks;
        this.mockGrade = mockGrade;
    }

    public String getDisplayString() {
        return displayString;
    }

    public ArrayList<String> getUngradedTasks() {
        return ungradedTasks;
    }

    public double getMockGrade() {
        return mockGrade;
    }
}
