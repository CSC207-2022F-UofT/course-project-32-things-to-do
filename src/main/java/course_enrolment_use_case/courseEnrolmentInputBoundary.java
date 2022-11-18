package course_enrolment_use_case;

// Use case layer

/*
* DONE
* */
public interface courseEnrolmentInputBoundary {
    courseEnrolmentResponseModel create(courseEnrolmentRequestModel requestModel);
}
