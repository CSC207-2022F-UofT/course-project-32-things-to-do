package course_tracker_use_cases;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.course_features.InMemoryCourse;
import screens.course_tracker.GradeCalculatorPresenter;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentDsGateway;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorInteractor;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorRequestModel;
import use_cases.course_tracker.grade_calculator_use_case.GradeCalculatorResponseModel;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    CourseEnrolmentDsGateway courseAccess;

    /**
     * Helper function to set up all the static variables
     */
    void setup() {
        //create a student user as the logged-in user
        StudentUser student = new StudentUser("testStudent", "testPassword");
        CurrentUser.setCurrentUser(student);

        //create some Task objects for the TaskMap
        Assignment assignment1 = new Assignment("testAssignment1", "testAssignment1_testStudent_testCourse",
                null, 0.2);
        assignment1.setComplete();
        assignment1.setGradeReceived(87);
        Assignment assignment2 = new Assignment("testAssignment2", "testAssignment2_testStudent_testCourse",
                null, 0.4);
        assignment2.setComplete();
        entities.Test test1 = new entities.Test("testTest1", "testTest1_testStudent_testCourse", null,
                null, 0.3);
        test1.setComplete();
        entities.Test test2 = new entities.Test("testTest2", "testTest2_testStudent_testCourse", null,
                null, 0.1);
        test2.setComplete();
        Event event1 = new Event("testEvent", "testEvent_testStudent_testCourse", 0, null,
                null, false, "");

        HashMap<String, Task> tasks = new HashMap<>();
        tasks.put("testAssignment1_testStudent_testCourse", assignment1);
        tasks.put("testAssignment2_testStudent_testCourse", assignment2);
        tasks.put("testTest1_testStudent_testCourse", test1);
        tasks.put("testTest2_testStudent_testCourse", test2);
        tasks.put("testEvent_testStudent_testCourse", event1);
        TaskMap.setTaskMap(tasks);

        Course course1 = new Course("testCourse", "", new ArrayList<>());
        HashMap<String, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseID(), course1);
        this.courseAccess = new InMemoryCourse(courseMap);
        ArrayList<String> courseList = new ArrayList<>();
        courseList.add("testCourse");
        ((StudentUser) CurrentUser.getCurrentUser()).setCourses(courseList);

    }

    /**
     * Helper function to clean up all the static variables
     */
    void tearDown() {
        CurrentUser.setCurrentUser(null);
        TaskMap.setTaskMap(null);
    }

    /**
     * Testing for exception thrown when user doesn't input the right number of values
     */
    @Test
    void failInvalidInputLength(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                fail("Use case test success unexpected. Invalid input length exception should have thrown.");
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                assertEquals("Invalid length of input.", error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "*95,85,69,90,*6", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for exception thrown when user doesn't input a star value indicating the target task(s)
     */
    @Test
    void failNoTarget(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                fail("Use case test success unexpected. No valid target exception should have thrown.");
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                assertEquals("No valid target task grade was read from input.", error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
            ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "77,89,90", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for exception thrown when user input non-numbers where program expects decimal values
     */
    @Test
    void failNonNumericalInput(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                fail("Use case test success unexpected. Invalid input exception should have thrown.");
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                assertEquals("Entered input is invalid. It should be a (decimal) number.", error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "77,*89,hello!", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for exception thrown when user inputs a course that they are not enrolled in
     */
    @Test
    void failNoSuchCourse(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                fail("Use case test success unexpected. Course not enrolled exception should have thrown.");
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                assertEquals("None of your enrolled courses match that course name.", error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("",
                "77,*89,67", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for successful test where user enters a valid input with one target
     */
    @Test
    void successOneTarget(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                assertEquals(80.33, responseModel.getRequiredGrade(), 0.01);
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                fail("Use case test fail unexpected: " + error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "77,*79,67", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for successful test where user enters a valid input with two different targets
     */
    @Test
    void successTwoTargets(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                assertEquals(97.00, responseModel.getRequiredGrade(), 0.01);
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                fail("Use case test fail unexpected: " + error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "77,*10,*87", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing for successful test where user enters a valid input with two different targets
     */
    @Test
    void failRequiredTooHigh(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        GradeCalculatorPresenter presenter = new GradeCalculatorPresenter(null) {
            @Override
            public GradeCalculatorResponseModel display(GradeCalculatorResponseModel responseModel) {
                fail("Use case test success unexpected. Required grade >100 exception should have thrown.");
                return null;
            }
            @Override
            public GradeCalculatorResponseModel failView(String error) {
                assertEquals("The required average for that goal grade is greater than 100.", error);
                return null;
            }
        };
        GradeCalculatorInteractor interactor = new GradeCalculatorInteractor(presenter, courseAccess);

        //prepare input data
        ArrayList<String> ungradedTasks = new ArrayList<>();
        ungradedTasks.add("testAssignment2");
        ungradedTasks.add("testTest1");
        ungradedTasks.add("testTest2");
        GradeCalculatorRequestModel request = new GradeCalculatorRequestModel("testCourse",
                "77,*10,*90", ungradedTasks);

        //run use case
        interactor.calculateGrade(request);

        //tear down static variables
        tearDown();
    }


}
