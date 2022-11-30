package screens.courses_features;

import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentInputBoundary;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentRequestModel;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentResponseModel;

public class CourseEnrolmentController {
    final CourseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(CourseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    CourseEnrolmentResponseModel create(String courseID, String instructorID, String studentID) {
        CourseEnrolmentRequestModel requestModel = new CourseEnrolmentRequestModel(
                courseID, instructorID, studentID);

        return enrolmentInput.create(requestModel);
    }

}
