package screens.course_features;

// Interfaces adapters layer

import use_cases.course_features.course_enrolment_use_case.*;

import javax.swing.*;

public class CourseEnrolmentPresenter implements CourseEnrolmentOutputBoundary {

    /**
     * Alert student user about course enrolment success
     * @param response output from the program (the student user that is enrolled in the course)
     */
    @Override
    public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel response) {
        JOptionPane.showMessageDialog(null, "Successfully enrolled!");
        return response;
    }

    /**
     * Alert student user about course enrolment failure
     * @param error the output from the program
     */
    @Override
    public CourseEnrolmentResponseModel prepareFailView(String error) {
        throw new CourseEnrolmentFailed(error);
    }
}
