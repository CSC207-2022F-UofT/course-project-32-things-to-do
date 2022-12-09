package screens.course_tracker;

import use_cases.course_tracker.grade_calculator_use_case.*;


/**
 * Presenter for the Grade Calculator use case
 * Located in the presenter/controller layer
 */
public class GradeCalculatorPresenter implements GradeCalculatorOutputBoundary {

    private final GradeCalculatorViewBoundary screen;

    public GradeCalculatorPresenter(GradeCalculatorViewBoundary screen) {
        this.screen = screen;
    }

    @Override
    public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
        String displayString = "Get an average of " + responseModel.getRequiredGrade() + "% in target tasks.";

        GradeCalculatorViewModel viewModel = new GradeCalculatorViewModel(displayString);

        screen.showRequiredGrade(viewModel);
        return responseModel;
    }

    @Override
    public GradeCalculatorResponseModel failView(String error) {
        throw new ProgressTrackingFail(error);
    }
}
