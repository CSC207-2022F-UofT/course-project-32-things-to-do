package screens.course_progress;

import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerOutputBoundary;
import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerResponseModel;

/**
 * Presenter for the Progress Tracker Use Case
 * Implements the Output Boundary as part of the dependency inversion
 */

public class ProgressTrackerPresenter implements ProgressTrackerOutputBoundary {

    /**
     * Reformat the display string from the responseModel based on the existing calculations
     *
     * @param responseModel the returned ProgressTrackerResponseModel from the user case interactor
     * @return the reformatted responseModel
     */
    @Override
    public ProgressTrackerResponseModel display(ProgressTrackerResponseModel responseModel) {

        double progress = responseModel.getCourseProgress();
        double mockGrade = responseModel.getMockGrade();
        double requiredAverage = responseModel.getRequiredAverage();

        String displayString = progress + "% of this course's work is completed! Current grade:  " + mockGrade;
        if (requiredAverage != -1) {
            displayString += " and required average remaining assessments: " + requiredAverage;
        }

        responseModel.setDisplayString(displayString);

        return responseModel;

    }

    /**
     * Inject a thrown exception from the Interactor into the view
     * @param error a String representing the error message
     * @return void
     */
    @Override
    public ProgressTrackerResponseModel failView(String error) {
        throw new ProgressTrackingFail(error);
    }
}
