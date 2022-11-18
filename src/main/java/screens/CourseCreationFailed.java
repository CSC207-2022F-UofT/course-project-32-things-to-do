package screens;

public class CourseCreationFailed extends RuntimeException {
    public CourseCreationFailed(String error) {
        super(error);
    }
}
