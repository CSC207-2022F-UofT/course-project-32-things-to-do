package use_cases.course_tracker.grade_calculator_use_case;

/**
 * Input Boundary for the Grade Calculator use case
 * Located in the use case layer, this interface is used for dependency inversion for controller to interactor
 */

public interface GradeCalculatorInputBoundary {
    void calculateGrade(GradeCalculatorRequestModel requestModel);
}
