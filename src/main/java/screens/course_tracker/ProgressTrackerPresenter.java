package screens.course_tracker;

import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerOutputBoundary;
import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerResponseModel;

/**
 * Presenter for the Progress Tracker Use Case
 * Implements the Output Boundary as part of the dependency inversion
 */
public class ProgressTrackerPresenter implements ProgressTrackerOutputBoundary {

    final ProgressTrackerViewBoundary viewBoundary;

    public ProgressTrackerPresenter(ProgressTrackerViewBoundary viewBoundary) {
        this.viewBoundary = viewBoundary;
    }

    /**
     * Reformat the display string from the responseModel based on the existing calculations
     *
     * @param responseModel the returned ProgressTrackerResponseModel from the user case interactor
     */
    @Override
    public void format(ProgressTrackerResponseModel responseModel) {
        double progress = responseModel.getCourseProgress();
        double mockGrade = responseModel.getMockGrade();
        double requiredAverage = responseModel.getRequiredAverage();

        StringBuilder displayString = new StringBuilder();
        displayString.append("Course progress in assessment weightage: ").append(progress).append("%\n");
        displayString.append("Current (accumulated and weighted) grade: ").append(mockGrade).append("%\n");
        if (requiredAverage != -1) {
            displayString.append(" Required average in remaining assessments: ").append(requiredAverage).append("%\n");
        }

        displayString.append("\nRemaining ungraded assessments in this course:\n");
        for (String taskName: responseModel.getUngradedTasks()) {
            displayString.append("  ").append(taskName).append("\n");
        }

        ProgressTrackerViewModel viewModel = new ProgressTrackerViewModel(displayString.toString(),
                responseModel.getUngradedTasks());

        viewBoundary.display(viewModel);

    }

    /**
     * Inject a thrown exception from the Interactor into the view
     * @param error a String representing the error message
     */
    @Override
    public void failView(String error) {
        throw new ProgressTrackingFail(error);
    }
}
