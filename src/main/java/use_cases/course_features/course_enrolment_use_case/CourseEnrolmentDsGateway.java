package use_cases.course_features.course_enrolment_use_case;


// Use case layer

public interface CourseEnrolmentDsGateway {
    // checks if student is in the course through the students argument of the Course
    // object (value) from the course id (key)
    boolean existsByStudent(String studentIdentifier);
    void save(CourseEnrolmentRequestModel requestModel);

//    void saveStudent(CourseEnrolmentRequestModel requestModel);
}
