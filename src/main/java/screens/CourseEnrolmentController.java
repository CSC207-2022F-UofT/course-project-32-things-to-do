package screens;

import course_enrolment_use_case.CourseEnrolmentInputBoundary;
import course_enrolment_use_case.CourseEnrolmentRequestModel;
import course_enrolment_use_case.CourseEnrolmentResponseModel;

public class CourseEnrolmentController {
    final CourseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(CourseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    CourseEnrolmentResponseModel create(String courseID, String instructorID) {
        CourseEnrolmentRequestModel requestModel = new CourseEnrolmentRequestModel(
                courseID, instructorID);

        return enrolmentInput.create(requestModel);
    }

}
