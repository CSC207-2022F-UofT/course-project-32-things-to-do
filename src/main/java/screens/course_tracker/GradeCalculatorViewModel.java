package screens.course_tracker;

/**
 * View data object for the GradeCalculator use case
 * Located in the presenter/controller layer, used in the dependency inversion between view and presenter
 */
public class GradeCalculatorViewModel {
    private final String displayMessage;

    public GradeCalculatorViewModel(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }
}
