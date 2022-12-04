package screens.course_tracker;

/**
 * ViewBoundary interface for the presenter to view dependency inversion
 * This interface is located in the controller/presenter layer
 */
public interface ProgressTrackerViewBoundary {
    void display(ProgressTrackerViewModel viewModel);
}
