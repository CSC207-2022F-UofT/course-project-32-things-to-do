package screens.course_progress;

/**
 * ViewBoundary interface for the presenter to view dependency inversion
 * This interface is located in the controller/presenter layer
 */

public interface ProgressTrackerViewBoundary {
    void dispaly(ProgressTrackerViewModel viewModel);
}
