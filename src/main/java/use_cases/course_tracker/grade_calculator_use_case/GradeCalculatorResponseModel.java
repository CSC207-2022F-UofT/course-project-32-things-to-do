package use_cases.course_tracker.grade_calculator_use_case;

/**
 * Output data object for the Grade Calculator use case
 * Located in the use case layer, used in the dependency inversion between the interactor and presenter
 */
public class GradeCalculatorResponseModel {
    private final double requiredGrade;

    public GradeCalculatorResponseModel(double requiredGrade) {
        this.requiredGrade = requiredGrade;
    }

    public double getRequiredGrade() {
        return requiredGrade;
    }
}
