package screens.course_features;

// Interface adapters layer

import use_cases.course_features.course_creation_use_case.CourseCreationOutputBoundary;
import use_cases.course_features.course_creation_use_case.CourseCreationResponseModel;

import javax.swing.*;

public class CourseCreationPresenter implements CourseCreationOutputBoundary {

    /**
     * Alert user to course creation success
     * @param response the output from the program
     */
    @Override
    public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel response) {

        JOptionPane.showMessageDialog(null, "New course created!");
        return response;
    }

    /**
     * Alert user to course creation failure
     * @param error the output from the program
     */
    @Override
    public CourseCreationResponseModel prepareFailView(String error) {
        throw new CourseCreationFailed(error);
    }
}
