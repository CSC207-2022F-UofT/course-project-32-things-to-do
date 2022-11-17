package course_creation_use_case;

// Use case layer

// Notes:
// ** the methods the repo needs to implement for the interactor to do its job

public interface courseCreationDSGateway {
//    boolean existsByCourseName(String courseIdentifier);
//    boolean existsByInstructorName(String instructorIdentifier);
    boolean existsByCourseID(String courseIdentifier);

    void saveCourse(courseCreationDSRequestModel requestModel);
}
