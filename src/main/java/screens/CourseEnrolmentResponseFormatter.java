package screens;

// Interfaces adapters layer

import course_enrolment_use_case.CourseEnrolmentPresenter;
import course_enrolment_use_case.CourseEnrolmentResponseModel;

public class CourseEnrolmentResponseFormatter implements CourseEnrolmentPresenter {

    @Override
    public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel response) {
        /* what is this */
        return response;
    }

    @Override
    public CourseEnrolmentResponseModel prepareFailView(String error) {
        throw new CourseEnrolmentFailed(error);
    }
}
