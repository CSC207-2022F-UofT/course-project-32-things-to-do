package course_creation_use_case;

// Use case layer

/*
Notes:
Done? i guess
 */

public interface courseCreationPresenter {
    /*
    when course is successfully created
     */
    courseCreationResponseModel prepareSuccessView(courseCreationResponseModel newCourse);

    /*
    when course name and instructor (courseID) already exists
     */
    courseCreationResponseModel prepareFailView(String error);
}