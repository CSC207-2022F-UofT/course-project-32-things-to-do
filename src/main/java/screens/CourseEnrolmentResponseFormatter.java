package screens;

// Interfaces adapters layer

import course_enrolment_use_case.CourseEnrolmentPresenter;
import course_enrolment_use_case.CourseEnrolmentResponseModel;

import javax.swing.*;

public class CourseEnrolmentResponseFormatter implements CourseEnrolmentPresenter {

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
