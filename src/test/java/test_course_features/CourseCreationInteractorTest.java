package test_course_features;

import entities.Course;
import org.junit.Test;
import screens.course_features.CourseCreationPresenter;
import screens.course_features.InMemoryCourse;
import use_cases.course_features.course_creation_use_case.*;

import java.io.IOException;
import java.util.ArrayList;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class CourseCreationInteractorTest {
    @Test
    public void create() throws IOException {
        CourseCreationDsGateway courseRepository = new InMemoryCourse();

        CourseCreationOutputBoundary presenter = new CourseCreationPresenter() {

            @Override
            public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse) {
                // 4. check output data and associated changes are correct
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
