package use_cases.course_features.course_enrolment_use_case;

// Use case layer

/**
 * Input boundary interface
 * Dependency inversion from controller to interactor
 */
public interface CourseEnrolmentInputBoundary {
    /**
     * the method in the use case interactor that does the entire enrolment process
     * @param requestModel the request model (information inputted by user)
     */
    void enrol(CourseEnrolmentRequestModel requestModel);
}
