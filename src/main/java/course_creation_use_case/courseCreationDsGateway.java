package course_creation_use_case;

// Use case layer

/*
Notes:
DONE?
- the methods the repo needs to implement for the interactor to do its job
 */

public interface courseCreationDsGateway {
//    boolean existsByCourseName(String courseIdentifier);
//    boolean existsByInstructorName(String instructorIdentifier);
    boolean existsByCourseID(String courseIdentifier);

//    void saveCourse(courseCreationDsRequestModel requestModel);
    void saveCourse(courseCreationRequestModel requestModel);
}
