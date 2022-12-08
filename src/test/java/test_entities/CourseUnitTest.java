package test_entities;

import entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Course entity
 * Assertion tests for whether all required fields are filled.
 */
class CourseUnitTest {
    @Test
    void course_allFieldsEmpty_thenIsFalse() {
        ArrayList<String> emptyArrayList = new ArrayList<>();
        Course course = new Course("", "", emptyArrayList);

        assertFalse(course.courseIsValid());
    }

    @Test
    void course_StrFilled_thenIsFalse() {
        ArrayList<String> emptyArrayList = new ArrayList<>();
        Course course = new Course("course1", "", emptyArrayList);

        assertFalse(course.courseIsValid());
    }
    @Test
    void course_taskFilled_thenIsFalse() {
        ArrayList<String> task = new ArrayList<>();
        task.add("task1");
        Course course = new Course("", "", task);

        assertFalse(course.courseIsValid());
    }

    @Test
    void course_taskAndStrFilled_thenIsFalse() {
        ArrayList<String> task = new ArrayList<>();
        task.add("task1");
        Course course = new Course("", "inst1", task);

        assertFalse(course.courseIsValid());
    }

    @Test
    void course_taskEmpty_thenIsFalse() {
        ArrayList<String> noTask = new ArrayList<>();
        Course course = new Course("course1", "inst1", noTask);

        assertFalse(course.courseIsValid());
    }

    @Test
    void course_allFieldsFilled_thenIsTrue() {
        ArrayList<String> task = new ArrayList<>();
        task.add("task1");
        Course course = new Course("course1", "inst1", task);

        assertTrue(course.courseIsValid());
    }
}
