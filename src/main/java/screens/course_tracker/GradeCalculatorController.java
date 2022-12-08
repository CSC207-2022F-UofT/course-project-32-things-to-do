package screens.course_tracker;

import use_cases.course_tracker.grade_calculator_use_case.*;

import java.util.ArrayList;

/**
 * Controller for the Grade Calculator Use Case
 * Located in the presenter/controller layer
 */
public class GradeCalculatorController {

    final GradeCalculatorInputBoundary gradeCalculatorInteractor;

    public GradeCalculatorController(GradeCalculatorInputBoundary gradeCalculatorInteractor) {
        this.gradeCalculatorInteractor = gradeCalculatorInteractor;
    }

    void calculateGrade(String courseName, String userInput, ArrayList<String> ungradedTasks) {

        GradeCalculatorRequestModel requestModel = new GradeCalculatorRequestModel(courseName, userInput,
                ungradedTasks);

        gradeCalculatorInteractor.calculateGrade(requestModel);
    }
}
