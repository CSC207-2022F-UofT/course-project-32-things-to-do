package course_enrolment_use_case;

// Use case layer

/*
* DONE
* */
public interface CourseEnrolmentInputBoundary {
    CourseEnrolmentResponseModel create(CourseEnrolmentRequestModel requestModel);
}
