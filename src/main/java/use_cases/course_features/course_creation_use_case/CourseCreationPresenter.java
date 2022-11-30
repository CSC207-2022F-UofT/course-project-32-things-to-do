package use_cases.course_features.course_creation_use_case;

// Use case layer

public interface CourseCreationPresenter {

    /**
     * Alerts user that course creation is successful (no existing course)
     * @param newCourse the output from the program
     */
    CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse);

    /**
     * Alerts user that course creation attempted failed
     * @param error the output from the program
     */
    CourseCreationResponseModel prepareFailView(String error);
}
