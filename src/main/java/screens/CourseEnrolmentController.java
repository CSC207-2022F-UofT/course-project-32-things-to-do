package screens;

import course_enrolment_use_case.courseEnrolmentInputBoundary;
import course_enrolment_use_case.courseEnrolmentRequestModel;
import course_enrolment_use_case.courseEnrolmentResponseModel;

public class CourseEnrolmentController {
    final courseEnrolmentInputBoundary enrolmentInput;
    public CourseEnrolmentController(courseEnrolmentInputBoundary enrolmentGateway) {
        this.enrolmentInput = enrolmentGateway;
    }

    courseEnrolmentResponseModel create(String courseID, String instructorID) {
        courseEnrolmentRequestModel requestModel = new courseEnrolmentRequestModel(
                courseID, instructorID);

        return enrolmentInput.create(requestModel);
    }

}
