package test_course_features;

//import entities.Course;
//import org.junit.Test;
//import screens.course_features.InMemoryCourse;
//import use_cases.course_features.course_creation_use_case.*;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class CourseCreationInteractorTest {
//    @Test
//    public void create() {
//        // 1. create interactor and prereq objects (args for interactor constructor parameters)
//        // 2. create input data
//        // 3. call use case input boundary method to run the use case
//        // 4. check output data passed to presenter is correct
//        // 5. check that the expected changes to the data layer are there
//
//        // 1. creating interactor and prereq objects
//        // "mock" saved data in a dictionary (won't be persistent)
//        CourseCreationDsGateway courseRepository = new InMemoryCourse();
//
//        // creates an anonymous implementing class for the output boundary
//        CourseCreationOutputBoundary outputBoundary = new CourseCreationOutputBoundary() {
//            @Override
//            public CourseCreationResponseModel prepareSuccessView(CourseCreationResponseModel newCourse) {
//                // 4. check output data and associated changes are correct
//                assertEquals("course1inst1", newCourse.getCourseID());
//                assertNotNull(newCourse.getTasks());
//                assertTrue(courseRepository.existsByCourseID("course1inst1"));
//                return null;
//            }
//
//            @Override
//            public CourseCreationResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        ArrayList<String> tasks = new ArrayList<>();
//        tasks.add("task1");
//        tasks.add("task2");
//        Course course = new Course("course1", "inst1", tasks);
//        CourseCreationInputBoundary interactor = new CourseCreationInteractor(courseRepository, outputBoundary);
//
//        // 2. input data: make up (normally would be created by the controller)
//        CourseCreationRequestModel inputData = new CourseCreationRequestModel(
//                "course1", "inst1", tasks);
//
//        // 3. run the use case
//        interactor.create(inputData);
//    }
//}
