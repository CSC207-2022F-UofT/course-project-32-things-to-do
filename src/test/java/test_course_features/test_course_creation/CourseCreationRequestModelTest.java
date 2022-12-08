package test_course_features.test_course_creation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.course_features.course_creation_use_case.CourseCreationRequestModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the methods of the request model
 */
class CourseCreationRequestModelTest {
    static CourseCreationRequestModel courseCreationRequestModel;

    @BeforeAll
    static void beforeAll() {
        ArrayList<String> badTasks = new ArrayList<>();
        badTasks.add("task1,task2,task3");
        courseCreationRequestModel = new CourseCreationRequestModel(
                "course1", "inst1", badTasks);
    }

    @Test
    void getCourseName() {
        assertEquals("course1", courseCreationRequestModel.getCourseName());
    }

    @Test
    void getInstructorName() {
        assertEquals("inst1", courseCreationRequestModel.getCourseInstructor());
    }

    @Test
    void getCourseID() {
        assertEquals("course1inst1", courseCreationRequestModel.getCourseID());
    }

    @Test
    void getTasks() {
        ArrayList<String> goodTasks = new ArrayList<>();
        goodTasks.add("task1");
        goodTasks.add("task2");
        goodTasks.add("task3");
        assertEquals(courseCreationRequestModel.getTasks(), goodTasks);
    }
}
