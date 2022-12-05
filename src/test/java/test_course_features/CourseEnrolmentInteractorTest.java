package test_course_features;

//import entities.Course;
//import entities.StudentUser;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import screens.course_features.InMemoryCourse;
//import screens.login_registration.InMemoryUser;
//import use_cases.course_features.course_creation_use_case.CourseCreationDsGateway;
//import use_cases.login_registration.user_register_usecase.UserRegGateway;
//
//import use_cases.course_features.course_enrolment_use_case.*;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
///**
// * TODO: NEED TO ADD THE TASK PART
// */
//class CourseEnrolmentInteractorTest {
//
//    @Test
//    public void enrol() {
//        // well technically interactor not done so ......
//
//        CourseEnrolmentDsGateway courseGateway = new InMemoryCourse();
//        CourseCreationDsGateway courseRepository = new InMemoryCourse();
//        UserRegGateway userRepository = new InMemoryUser();
//
//        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
//            @Override
//            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel newStudent) {
//                Assertions.assertEquals("course1inst1", newStudent.getCourseID());
//                Assertions.assertEquals("user1", newStudent.getStudentID());
//                Assertions.assertTrue(courseRepository.existsByCourseID("course1inst1"));
//                Assertions.assertTrue(userRepository.existsByName("user1"));
//                return null;
//            }
//
//            @Override
//            public CourseEnrolmentResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        // make course
//        ArrayList<String> tasks = new ArrayList<>();
//        tasks.add("task1");
//        tasks.add("task2");
//        Course course = new Course("course1", "inst1", tasks);
//
//        // make student
//        StudentUser student = new StudentUser("user1", "pass1");
//
//        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(courseGateway, outputBoundary);
//
//        CourseEnrolmentRequestModel inputData = new CourseEnrolmentRequestModel(
//                "course1", "inst1", "user1");
//
//        interactor.enrol(inputData);
//    }
//}