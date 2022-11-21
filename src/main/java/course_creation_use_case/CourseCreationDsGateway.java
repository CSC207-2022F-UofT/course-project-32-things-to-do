package course_creation_use_case;

// Use case layer

/*
Notes:
- the methods the repo needs to implement for the interactor to do its job
 */

public interface CourseCreationDsGateway {
    boolean existsByCourseID(String courseIdentifier);

    void saveCourse(CourseCreationRequestModel requestModel);
}
