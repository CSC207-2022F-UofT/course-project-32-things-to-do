package course_enrolment_and_creation_usecase;

// Use case layer

// Notes:
// ** the methods the repo needs to implement for the interactor to do its job


public interface courseCreationDSGateway {
//    boolean existsByCourseName(String courseIdentifier);
//    boolean existsByInstructorName(String instructorIdentifier);
    boolean existsByCourseID(String courseIdentifier);

    void saveCourse(courseCreationRequestModel requestModel);
}
