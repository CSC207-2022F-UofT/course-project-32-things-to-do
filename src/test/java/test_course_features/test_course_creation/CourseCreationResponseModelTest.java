package test_course_features.test_course_creation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.course_features.course_creation_use_case.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing methods of the response model
 */
public class CourseCreationResponseModelTest {
    static CourseCreationResponseModel response;

    @BeforeAll
    static void beforeAll() {
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("task1");
        tasks.add("task2");
        tasks.add("task3");
        response = new CourseCreationResponseModel("id", tasks);
    }

    @Test
    void getCourseID() {
        assertEquals("id", response.getCourseID());
    }

    @Test
    void getTasks() {
        ArrayList<String> taskTesting = new ArrayList<>();
        taskTesting.add("task1");
        taskTesting.add("task2");
        taskTesting.add("task3");
        assertEquals(response.getTasks(), taskTesting);
    }
}
