package course_creation_use_case;

// Use case layer

/*
Notes:
Done? i guess
 */

public interface CourseCreationPresenter {
    /*
    when course is successfully created
     */
    CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse);

    /*
    when course name and instructor (courseID) already exists
     */
    CourseCreationResponseModel prepareFailView(String error);
}
