package use_cases.course_features.course_enrolment_use_case;

// Use case layer


/**
 * dependency inversion for interactor to presenter
 */
public interface CourseEnrolmentOutputBoundary {

    /**
     * Alerts student user that course enrolment is successful
     * @param newStudent output from the program (the student user that is enrolled in the course)
     */
    CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel newStudent);

    /**
     * Alerts student user that course enrolment attempt failed
     * @param error the output from the program
     */
    CourseEnrolmentResponseModel prepareFailView(String error);
}
