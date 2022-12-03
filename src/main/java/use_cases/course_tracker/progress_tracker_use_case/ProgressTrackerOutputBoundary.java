package use_cases.course_tracker.progress_tracker_use_case;

/**
 * Output Boundary for the Progress Tracker Use Case
 * (inverts dependency for interactor to presenter)
 */

public interface ProgressTrackerOutputBoundary {
    void format(ProgressTrackerResponseModel responseModel);

    void failView(String error);
}
