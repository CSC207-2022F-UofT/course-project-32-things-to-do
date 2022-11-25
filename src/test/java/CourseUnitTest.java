import entities.Course;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourseUnitTest {

    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        ArrayList<String> task = new ArrayList<String>();
        task.add("task1");
        Course course = new Course("csc207", "paulgries", task);
    }
}
