package screens.courses_features;

public class CourseEnrolmentFailed extends RuntimeException {
    public CourseEnrolmentFailed(String error) {
        super(error);
    }
}
