package screens.course_features;

import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentInputBoundary;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentRequestModel;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentResponseModel;

public class CourseEnrolmentController {
    final CourseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(CourseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    CourseEnrolmentResponseModel enrol(String courseID, String instructorID) {
        CourseEnrolmentRequestModel requestModel = new CourseEnrolmentRequestModel(
                courseID, instructorID);

        return enrolmentInput.enrol(requestModel);
    }
}
