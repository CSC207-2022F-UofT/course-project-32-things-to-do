package use_cases.course_tracker.progress_tracker_use_case;

/**
 * Input Boundary interface for the Progress Tracker Use Case
 * (inverts dependency from Controller to Interactor)
 */

public interface ProgressTrackerInputBoundary {
    ProgressTrackerResponseModel trackProgress(ProgressTrackerRequestModel requestModel);
}
