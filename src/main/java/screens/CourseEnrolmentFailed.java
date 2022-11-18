package screens;


public class CourseEnrolmentFailed extends RuntimeException {
    public CourseEnrolmentFailed(String error) {
        super(error);
    }
}
