package test_course_features.course_creation;

import entities.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.course_features.CourseCreationController;
import screens.course_features.CourseCreationFailed;
import screens.course_features.CourseCreationPresenter;
import use_cases.course_features.course_creation_use_case.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CourseCreationControllerTest {
    private CourseCreationController controller;

    @BeforeAll
    static void setUp() {
        CourseCreationDsGateway gateway = new CourseCreationDsGateway() {
            @Override
            public boolean existsByCourseID(String courseIdentifier) {
                return false;
            }

            @Override
            public void save(Course requestModel) throws IOException {

            }
        };
        CourseCreationOutputBoundary presenter = new CourseCreationPresenter();
        CourseCreationInputBoundary interactor = new CourseCreationInteractor(gateway, presenter);
        CourseCreationController controller = new CourseCreationController(interactor);
    }

    @Test
    void what() {
        Exception e;
    }
}
