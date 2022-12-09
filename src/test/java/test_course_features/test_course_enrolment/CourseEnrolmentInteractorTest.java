package test_course_features.test_course_enrolment;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.course_features.InMemoryCourse;
import screens.task_management.InMemoryTaskMap;
import use_cases.course_features.course_enrolment_use_case.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the components of the course enrolment interactor

 */
class CourseEnrolmentInteractorTest {

    CourseEnrolmentCourseDsGateway courseAccess;
//    TaskMapGateway taskAccess;
    CourseEnrolmentTaskDsGateway taskAccess;


    /**
     * helper function to set up all static variables
     * this is what the data is BEFORE the use case is run
     */
    void setUp() {
        // create student user as the logged-in user
        StudentUser studentbefore = new StudentUser("julie", "password1");
        CurrentUser.setCurrentUser(studentbefore);

        // instructor creating task objects for a course (these would be added to task map)
        Assignment a1 = new Assignment("a1", "a1_paul_none", null, 0.1);
        Assignment a2 = new Assignment("a2", "a2_paul_none", null, 0.1);

        HashMap<String, Task> taskmapbefore = new HashMap<>();
        taskmapbefore.put("a1_paul_none", a1);
        taskmapbefore.put("a2_paul_none", a2);
        TaskMap.setTaskMap(taskmapbefore);

        // list of task  titles for course creation
        ArrayList<String> taskList = new ArrayList<>();
        taskList.add("a1");
        taskList.add("a2");

        // create course that instructor makes, populate with tasks
        Course coursebefore = new Course("csc207", "paul", taskList);
        HashMap<String, Course> coursemapbefore = new HashMap<>();
        coursemapbefore.put(coursebefore.getCourseID(), coursebefore);
        this.courseAccess = new InMemoryCourse(coursemapbefore);

        taskAccess = new InMemoryTaskMap();
        taskAccess.saveNewMaptoMap(TaskMap.getTaskMap());
    }

    /**
     * helper function to clean up add static variables to reduce memory storage
     */
    void tearDown() {
        CurrentUser.setCurrentUser(null);
        TaskMap.setTaskMap(null);
    }

    // 1. course checks!

    /**
     * Test for whether student id was successfully appended to Course's 'students' parameter
     * ie
     * before: Course("csc207", "paul", "csc207paul", ["a1", "a2"], new ArrayList<>())
     * after: Course("csc207", "paul", "csc207paul", ["a1", "a2"], "julie")
     */
    @Test
    void isStudentInCourse() {
        setUp(); // setting up static variables

        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                assertTrue(courseAccess.searchForCourse(responseModel.getCourseID()).getStudents().contains("julie"));
                assertEquals("julie", CurrentUser.getCurrentUser().getName());
                return null;
            }
            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "csc207", "paul");
        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                courseAccess, taskAccess, outputBoundary);

        interactor.enrol(request);

        tearDown();
    }

    /**
     * tests whether a new map of tasks (with student username in id) is added to taskmap
     * ie
     * newtaskmap = HashMap(<"a1_julie_csc207", Assignment(...)>, <"a2_julie_csc207", Assignment(...)>)
     * before: TaskMap(<"a1_paul_csc207", Assignment(...)>, <"a2_paul_csc207", Assignment(...)>)
     * after: TaskMap(<"a1_paul_csc207", Assignment(...)>, <"a2_paul_csc207", Assignment(...)>,
     *          <"a1_julie_csc207", Assignment(...)>, <"a2_julie_csc207", Assignment(...)>)
     */
    @Test
    void newMapToTaskMap() {
        setUp(); // setting up static variables

        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {

                // 2 tasks are added on top of 2 existing tasks
                assertTrue(TaskMap.getTaskMap().containsKey("a1_julie_csc207"));


                // go to taskmap, check if ids contain key tasks
                assertTrue(responseModel.getTasks().contains("a1_julie_csc207"));

                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "csc207", "paul");
        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                courseAccess, taskAccess, outputBoundary);

        interactor.enrol(request);

        tearDown();
    }

    /**
     * tests whether the course a student enrolled in is added to the 'courses' parameter
     * ie
     * before: currentUser is StudentUser("julie", "pwd", ["task1_julie_null"], arraylist1, new ArrayList<>(), arraylist2, hashmap1)
     * after: currentUser is StudentUser("julie", "pwd", ["task1_julie_null"], arraylist1, ["csc207paul"], arraylist2, hashmap1)
     */
    @Test
    void courseAddedtoStudentCourses() {
        setUp(); // setting up static variables

        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                assertTrue(((StudentUser) CurrentUser.getCurrentUser()).getCourses().contains("csc207paul"));
                assertEquals("csc207paul", ((StudentUser) CurrentUser.getCurrentUser()).getCourses().get(0));
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "csc207", "paul");
        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                courseAccess, taskAccess, outputBoundary);

        interactor.enrol(request);

        tearDown();
    }

    /**
     * tests whether course tasks ids (student user) are in the student's to-do list
     * before: currentUser is StudentUser("julie", "pwd", ["task1_julie_null"], arraylist1, ["csc207paul"], arraylist2, hashmap1)
     * after: StudentUser("julie", "pwd", ["task1_julie_null, "a1_julie_csc207", "a2_julie_csc207"], arraylist1, ["csc207paul"], arraylist2, hashmap1)
     */
    @Test
    void todolistUpdated() {
        setUp(); // setting up static variables

        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                assertTrue(responseModel.getTasks().contains("a1_julie_csc207"));
                assertTrue(responseModel.getTasks().contains("a2_julie_csc207"));
                assertTrue(((StudentUser) CurrentUser.getCurrentUser()).getToDoList().contains("a1_julie_csc207"));
                assertTrue(((StudentUser) CurrentUser.getCurrentUser()).getToDoList().contains("a2_julie_csc207"));
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "csc207", "paul");
        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                courseAccess, taskAccess, outputBoundary);

        interactor.enrol(request);

        tearDown();
    }
}
