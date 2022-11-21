package course_enrolment_use_case;

// Use case layer

public interface CourseEnrolmentInputBoundary {
    CourseEnrolmentResponseModel create(CourseEnrolmentRequestModel requestModel);
}
