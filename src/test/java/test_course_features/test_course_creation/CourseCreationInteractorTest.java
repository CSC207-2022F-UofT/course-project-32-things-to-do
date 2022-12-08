package test_course_features.test_course_creation;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.course_features.*;
import use_cases.course_features.course_creation_use_case.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the creation of a new course
 */
class CourseCreationInteractorTest {
    @Test
    void createCourse() {
        InMemoryCourse courseGateway = new InMemoryCourse();

        CourseCreationOutputBoundary outputBoundary = new CourseCreationOutputBoundary() {
            @Override
            public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse) {
                ArrayList<String> tasks = new ArrayList<>();
                tasks.add("task1");
                tasks.add("task2");
                tasks.add("task3");
                Course course1 = new Course("course1", "inst1", tasks);

                assertEquals("course1inst1", course1.getCourseID());
                assertEquals(course1.getTasks(), tasks);
                assertNotNull(course1.getTasks());
                assertTrue(courseGateway.existsByCourseID(course1.getCourseID()));
                return null;
            }

            @Override
            public CourseCreationResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        ArrayList<String> requestTask = new ArrayList<>();

        requestTask.add("task1,task2,task3");
        CourseCreationRequestModel request = new CourseCreationRequestModel(
                "course1", "inst1", requestTask);
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(courseGateway, outputBoundary);

        interactor.create(request);
    }
}
