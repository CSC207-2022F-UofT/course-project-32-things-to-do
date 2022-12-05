package screens.course_tracker;

import java.util.ArrayList;

/**
 * Data object for presenter to view dependency inversion
 * ViewModel is located in the controller/presenter layer
 */
public class ProgressTrackerViewModel {
    private final String displayString;

    private final ArrayList<String> ungradedTasks;

    public ProgressTrackerViewModel(String displayString, ArrayList<String> ungradedTasks) {
        this.displayString = displayString;
        this.ungradedTasks = ungradedTasks;
    }

    public String getDisplayString() {
        return displayString;
    }

    public ArrayList<String> getUngradedTasks() {
        return ungradedTasks;
    }

}
