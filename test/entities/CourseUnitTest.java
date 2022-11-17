package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import Entities.Course;
public class CourseUnitTest {

    @Test
    void givenCourse_whenCourseExists_thenIsFalse() {
        Course course = new Course("csc207", "abc", "tasks idk");

        assertFalse(course.courseExists);
    }
}
