package course_enrolment_and_creation_usecase;

// Use case layer

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
