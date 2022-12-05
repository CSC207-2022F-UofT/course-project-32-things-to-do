package screens.course_tracker;

/**
 * View Boundary for the Grade Calculator use case
 * Located in the presenter/controller layer, used to invert the dependency between the view and presenter
 */
public interface GradeCalculatorViewBoundary {
    void showRequiredGrade(GradeCalculatorViewModel viewModel);
}
