package use_cases.course_features.course_creation_use_case;

// Use case layer


/*
Notes:
- the public interface for calling the use case
- boundaries are interfaces
- input boundary is between the controller and use case
 */

public interface CourseCreationInputBoundary {
    CourseCreationResponseModel create(CourseCreationRequestModel requestModel);
}
