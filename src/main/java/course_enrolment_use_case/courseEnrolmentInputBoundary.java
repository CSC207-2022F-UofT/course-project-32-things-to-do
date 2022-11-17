package course_enrolment_use_case;

// Use case layer

public interface courseEnrolmentInputBoundary {
    courseEnrolmentResponseModel create(courseEnrolmentRequestModel requestModel);
}
