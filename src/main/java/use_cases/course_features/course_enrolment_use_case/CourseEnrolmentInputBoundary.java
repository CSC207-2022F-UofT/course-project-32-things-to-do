package use_cases.course_features.course_enrolment_use_case;

// Use case layer

public interface CourseEnrolmentInputBoundary {
    CourseEnrolmentResponseModel create(CourseEnrolmentRequestModel requestModel);
}
