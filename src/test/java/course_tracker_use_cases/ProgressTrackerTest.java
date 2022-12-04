package course_tracker_use_cases;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.course_tracker.ProgressTrackerPresenter;
import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerInteractor;
import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerRequestModel;
import use_cases.course_tracker.progress_tracker_use_case.ProgressTrackerResponseModel;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class ProgressTrackerTest {
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
        test1.setGradeReceived(66.5);
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
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course1.getCourseID());
        student.setCourses(courses);
        CourseMap.initializeEmpty();
        CourseMap.addCourse(course1.getCourseID(), course1);

    }

    /**
     * Helper function to clean up all the static variables
     */
    void tearDown() {
        CurrentUser.setCurrentUser(null);
        TaskMap.setTaskMap(null);
        CourseMap.initializeEmpty();
    }

    /**
     * Testing that exception is thrown for invalid numerical input
     */
    @Test
    void failInvalidInput(){
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        ProgressTrackerPresenter presenter = new ProgressTrackerPresenter(null) {
            @Override
            public void format(ProgressTrackerResponseModel responseModel) {
                fail("Use case success not expected. Exception for invalid input was not thrown");
            }
            @Override
            public void failView(String error) {
                assertEquals("Entered input is invalid. It should be a (decimal) " +
                                "number with no special characters.", error);
            }
        };
        ProgressTrackerInteractor interactor = new ProgressTrackerInteractor(presenter);

        //prepare input data
        ProgressTrackerRequestModel request = new ProgressTrackerRequestModel("testCourse", "",
                "95%", "!@#$");

        //run use case
        interactor.trackProgress(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing that exception is thrown for course title that logged-in user is not enrolled in
     */
    @Test
    void failNoSuchCourse() {
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        ProgressTrackerPresenter presenter = new ProgressTrackerPresenter(null) {
            @Override
            public void format(ProgressTrackerResponseModel responseModel) {
                fail("Use case success not expected. Exception for course not enrolled was not thrown");
            }
            @Override
            public void failView(String error) {
                assertEquals("None of your enrolled courses match that course name.", error);
            }
        };
        ProgressTrackerInteractor interactor = new ProgressTrackerInteractor(presenter);

        //prepare input data
        ProgressTrackerRequestModel request = new ProgressTrackerRequestModel("csc207", "",
                "-1", "-1");

        //run use case
        interactor.trackProgress(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing return calculations for just course name as input (no setting calls)
     */
    @Test
    void successNoSetterInput() {
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        ProgressTrackerPresenter presenter = new ProgressTrackerPresenter(null) {
            @Override
            public void format(ProgressTrackerResponseModel responseModel) {
                assertNotNull(responseModel);
                assertEquals(100, responseModel.getCourseProgress(), 0.01);
                assertEquals(74.70, responseModel.getMockGrade(), 0.01);
                assertEquals(-1.0, responseModel.getRequiredAverage(), 0.01);
                assertEquals(2, responseModel.getUngradedTasks().size());
            }
            @Override
            public void failView(String error) {
                fail("Test failure is unexpected.");
            }
        };
        ProgressTrackerInteractor interactor = new ProgressTrackerInteractor(presenter);

        //prepare input data
        ProgressTrackerRequestModel request = new ProgressTrackerRequestModel("testCourse",
                "", "-1", "-1");

        //run use case
        interactor.trackProgress(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing return calculations case where goalGrade and newGradeReceived are inputted by user
     */
    @Test
    void successWithSetterInput() {
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        ProgressTrackerPresenter presenter = new ProgressTrackerPresenter(null) {
            @Override
            public void format(ProgressTrackerResponseModel responseModel) {
                assertNotNull(responseModel);
                assertEquals(100, responseModel.getCourseProgress(), 0.01);
                assertEquals(74.05, responseModel.getMockGrade(), 0.01);
                assertEquals(88.92, responseModel.getRequiredAverage(), 0.01);
                assertEquals(1, responseModel.getUngradedTasks().size());
            }
            @Override
            public void failView(String error) {
                fail("Test failure is unexpected.");
            }
        };
        ProgressTrackerInteractor interactor = new ProgressTrackerInteractor(presenter);

        //prepare input data
        ProgressTrackerRequestModel request = new ProgressTrackerRequestModel("testCourse",
                "testTest2", "70.8", "80");

        //run use case
        interactor.trackProgress(request);

        //tear down static variables
        tearDown();
    }

    /**
     * Testing return calculations case where goalGrade and newGradeReceived are inputted by user
     * and the required average is not a valid grade
     */
    @Test
    void failAverageTooHigh() {
        //set up static variables
        setup();

        //initialize the interactor and prerequisite objects
        ProgressTrackerPresenter presenter = new ProgressTrackerPresenter(null) {
            @Override
            public void format(ProgressTrackerResponseModel responseModel) {
                fail("Success not expected. Required average too high exception should have thrown.");
            }
            @Override
            public void failView(String error) {
                assertEquals("The required average for that goal grade is greater than 100.", error);
            }
        };
        ProgressTrackerInteractor interactor = new ProgressTrackerInteractor(presenter);

        //prepare input data
        ProgressTrackerRequestModel request = new ProgressTrackerRequestModel("testCourse",
                "testTest2", "70.8", "100");

        //run use case
        interactor.trackProgress(request);

        //tear down static variables
        tearDown();
    }

}
