package screens;

// Interfaces adapters layer

import course_enrolment_use_case.courseEnrolmentPresenter;
import course_enrolment_use_case.courseEnrolmentResponseModel;

public class CourseEnrolmentResponseFormatter implements courseEnrolmentPresenter {

    @Override
    public courseEnrolmentResponseModel prepareSuccessView(courseEnrolmentResponseModel response) {
        /* what is this */
        return response;
    }

    @Override
    public courseEnrolmentResponseModel prepareFailView(String error) {
        throw new CourseEnrolmentFailed(error);
    }
}
