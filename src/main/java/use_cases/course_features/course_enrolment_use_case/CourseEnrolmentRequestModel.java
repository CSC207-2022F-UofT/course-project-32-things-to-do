package use_cases.course_features.course_enrolment_use_case;

// Use case layer

/**
 * The request model for the course enrolment use case
 * this is kind of like the input data object
 */
public class CourseEnrolmentRequestModel {
    private final String courseName;
    private final String courseInstructor;
    private final String courseID;
//    private final String studentID;

    /**
     * Creates a request model with the given input
     * @param courseName the name of the course
     * @param courseInstructor the instructor of the course
     */
    public CourseEnrolmentRequestModel(String courseName, String courseInstructor) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public String getCourseID() {
        return courseID;
    }

}
