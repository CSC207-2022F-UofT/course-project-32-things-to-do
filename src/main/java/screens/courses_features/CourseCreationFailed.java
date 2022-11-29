package screens.courses_features;

public class CourseCreationFailed extends RuntimeException {
    public CourseCreationFailed(String error) {
        super(error);
    }
}
