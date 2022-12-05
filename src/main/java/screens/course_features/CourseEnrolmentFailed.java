package screens.course_features;

public class CourseEnrolmentFailed extends RuntimeException {
    public CourseEnrolmentFailed(String error) {
        super(error);
    }
}
