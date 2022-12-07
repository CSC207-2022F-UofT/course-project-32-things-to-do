package test_course_features;

import entities.Course;
import org.junit.Test;
import screens.course_features.CourseCreationPresenter;
import screens.course_features.InMemoryCourse;
import use_cases.course_features.course_creation_use_case.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class CourseCreationInteractorTest {
    /**
     * testing the creation of an assignment
     */
    @Test
    public void createCourse() {
        InMemoryCourse courseGateway = new InMemoryCourse();

        CourseCreationOutputBoundary outputBoundary = new CourseCreationOutputBoundary() {
            @Override
            public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse) {
                ArrayList<String> tasks = new ArrayList<String>();
                tasks.add("task1");
                tasks.add("task2");
                tasks.add("task3");
                Course course1 = new Course("course1", "inst1", tasks);

                assertEquals(course1.getCourseID(), "course1inst1");
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
        CourseCreationRequestModel request = new CourseCreationRequestModel(
                "course2", "inst2", );
    }
    @Test
    public void create() throws IOException {
        CourseCreationDsGateway courseRepository = new InMemoryCourse();

        CourseCreationOutputBoundary presenter = new CourseCreationPresenter() {

            @Override
            public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse) {
                ArrayList<String> tasks = new ArrayList<String>();
                tasks.add("task1");
                tasks.add("task2");
                tasks.add("task3");
                Course course1 = new Course("course1", "inst1", tasks);
                assertEquals("course1inst1", newCourse.getCourseID());
                assertNotNull(newCourse.getTasks());
                assertTrue(courseRepository.existsByCourseID("course1inst1"));
                return null;
            }

            @Override
            public CourseCreationResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("task1");
        tasks.add("task2");
        Course course = new Course("course1", "inst1", tasks);
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(courseRepository, presenter);

        CourseCreationRequestModel inputData = new CourseCreationRequestModel(
                "course1", "inst1", tasks);

        interactor.create(inputData);
    }
}
