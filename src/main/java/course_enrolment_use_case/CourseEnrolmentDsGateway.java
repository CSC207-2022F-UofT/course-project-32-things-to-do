package course_enrolment_use_case;

// Use case layer

public interface CourseEnrolmentDsGateway {
    /* checks if student is in the course */
    boolean existsByStudent(String studentIdentifier);

//    void saveStudent(courseEnrolmentDsRequestModel requestModel);
    void saveStudent(CourseEnrolmentRequestModel requestModel);
}
