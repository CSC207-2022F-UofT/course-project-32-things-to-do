import entities.Course;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourseUnitTest {


    /**
     * Tests needed:
     *
     * try catch exceptions:
     * - name empty, instructor empty, task empty
     * - course id already in course map
     * - assertThrows();
     * or can just do assertFalse(course.____IsValid());
     */
    @Test

    void huh() {
        ArrayList<String> task = new ArrayList<String>();
        task.add("task1");
        Course course = new Course("csc207", "paulgries", task);
    }
}
