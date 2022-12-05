package use_cases.course_tracker.grade_calculator_use_case;

/**
 * Output Boundary for the Grade Calculator use case
 * Located in the use case layer, inverts the dependency between the interactor and the presenter
 */
public interface GradeCalculatorOutputBoundary {
    GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel);

    GradeCalculatorResponseModel failView(String error);
}
