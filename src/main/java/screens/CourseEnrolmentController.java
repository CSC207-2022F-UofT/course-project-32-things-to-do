package screens;

import course_enrolment_use_case.CourseEnrolmentInputBoundary;
import course_enrolment_use_case.CourseEnrolmentRequestModel;
import course_enrolment_use_case.CourseEnrolmentResponseModel;

public class CourseEnrolmentController {
    final CourseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(CourseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    CourseEnrolmentResponseModel enrol(String courseID, String instructorID, String studentID) {
        CourseEnrolmentRequestModel requestModel = new CourseEnrolmentRequestModel(
                courseID, instructorID, studentID);

        return enrolmentInput.enrol(requestModel);
    }

}
