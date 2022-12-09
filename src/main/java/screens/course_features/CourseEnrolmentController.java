package screens.course_features;

import use_cases.course_features.course_enrolment_use_case.*;

public class CourseEnrolmentController {
    final CourseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(CourseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    void enrol(String courseID, String instructorID) {
        CourseEnrolmentRequestModel requestModel = new CourseEnrolmentRequestModel(
                courseID, instructorID);

        enrolmentInput.enrol(requestModel);
    }
}
