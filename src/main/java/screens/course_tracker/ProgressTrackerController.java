package screens.course_tracker;

import use_cases.course_tracker.progress_tracker_use_case.*;

/**
 * Controller for the Progress Tracker Use Case
 * Located in the Interface Adapter Layer, triggers the Interactor
 */
public class ProgressTrackerController {

    final ProgressTrackerInputBoundary progressInput;

    public ProgressTrackerController(ProgressTrackerInputBoundary progressInput) {
        this.progressInput = progressInput;
    }

    void trackProgress(String courseName, String newGradeTaskName, String newGrade,
                                               String newGoalGrade) {
        ProgressTrackerRequestModel requestModel = new ProgressTrackerRequestModel(courseName,
                newGradeTaskName, newGrade, newGoalGrade);

        progressInput.trackProgress(requestModel);
    }
}
