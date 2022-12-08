package test_course_features.course_enrolment;

import entities.*;
import org.junit.jupiter.api.Test;
import screens.course_features.InMemoryCourse;
import screens.task_management.InMemoryTaskMap;
import use_cases.course_features.course_enrolment_use_case.*;
import use_cases.task_management.read_write.TaskMapGateway;

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

        // create instructor
        InstructorUser instructor = new InstructorUser("paul", "password1");

        // instructor creating task objects for a course (these would be added to task map)
        Assignment a1 = new Assignment("a1", "a1_paul_csc207", null, 0.1);
        Assignment a2 = new Assignment("a2", "a2_paul_csc207", null, 0.1);
//        entities.Test tt1 = new entities.Test(
//                "tt1", "tt1_inst1_course1", null, null, 0.1);

        HashMap<String, Task> taskmapbefore = new HashMap<>();
        taskmapbefore.put("a1_inst1_course1", a1);
        taskmapbefore.put("a2_inst1_course1", a2);
//        taskmapbefore.put("tt1_inst1_course1", tt1);
        TaskMap.setTaskMap(taskmapbefore);

        // create course that instructor makes, populate with tasks
        Course coursebefore = new Course("csc207", "paul", new ArrayList<>());
        HashMap<String, Course> coursemapbefore = new HashMap<>();
        coursemapbefore.put(coursebefore.getCourseID(), coursebefore);
        this.courseAccess = new InMemoryCourse(coursemapbefore);
        ArrayList<String> courselistbefore = new ArrayList<>();
        courselistbefore.add("a1_inst1_course1");
        courselistbefore.add("a2_inst1_course1");
//        courselistbefore.add("tt1_inst1_course1");
        ((StudentUser) CurrentUser.getCurrentUser()).setCourses(courselistbefore);

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

        // initialize interactor and prereq objects
        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                // TODO: assertEquals and more
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "course1", "inst1");
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
    void newMaptoTaskMap() {
        setUp(); // setting up static variables

        // initialize interactor and prereq objects
        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                // TODO: assertEquals and more
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "course1", "inst1");
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

        // initialize interactor and prereq objects
        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                // TODO: assertEquals and more
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "course1", "inst1");
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

        // initialize interactor and prereq objects
        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertNotNull(responseModel);
                // TODO: assertEquals and more
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        CourseEnrolmentRequestModel request = new CourseEnrolmentRequestModel(
                "course1", "inst1");
        CourseEnrolmentInputBoundary interactor = new CourseEnrolmentInteractor(
                courseAccess, taskAccess, outputBoundary);

        interactor.enrol(request);

        tearDown();
    }


}
