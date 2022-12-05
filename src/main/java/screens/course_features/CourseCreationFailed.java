package screens.course_features;

public class CourseCreationFailed extends RuntimeException {
    public CourseCreationFailed(String error) {
        super(error);
    }
}
