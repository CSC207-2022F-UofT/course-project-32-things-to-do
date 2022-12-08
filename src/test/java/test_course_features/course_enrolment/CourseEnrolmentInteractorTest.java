package test_course_features.course_enrolment;

import entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.course_features.CourseEnrolmentPresenter;
import screens.course_features.InMemoryCourse;
import screens.task_management.InMemoryTaskMap;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_features.course_enrolment_use_case.*;
import use_cases.task_management.read_write.TaskMapGateway;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the components of a course enrolment
 * which is basically every line of code in my interactor because clearly there are buuuuugs
 * acc maybe this is why i should be doing tests while i code oop
 */
public class CourseEnrolmentInteractorTest {

    CourseEnrolmentCourseDsGateway courseAccess;
//    TaskMapGateway taskAccess;
    CourseEnrolmentTaskDsGateway taskAccess;

    /**
     * helper function to set up all static variables
     */
    void setUp() {
        // create student user as the logged-in user
        StudentUser student = new StudentUser("student1", "password1");
        CurrentUser.setCurrentUser(student);

        // create instructor
        InstructorUser instructor = new InstructorUser("inst1", "password1");

        // instructor creating task objects for a course (these would be added to task map)
        Assignment a1 = new Assignment("a1", "a1_inst1_course1", null, 0.1);
        Assignment a2 = new Assignment("a2", "a2_inst1_course1", null, 0.1);
        entities.Test tt1 = new entities.Test(
                "tt1", "tt1_inst1_course1", null, null, 0.1);

        HashMap<String, Task> tasks = new HashMap<>();
        tasks.put("a1_inst1_course1", a1);
        tasks.put("a2_inst1_course1", a2);
        tasks.put("tt1_inst1_course1", tt1);
        TaskMap.setTaskMap(tasks);

        // create course that instructor makes, populate with tasks
        Course course1 = new Course("course1", "inst1", new ArrayList<>());
        HashMap<String, Course> courseMap = new HashMap<>();
        courseMap.put(course1.getCourseID(), course1);
        this.courseAccess = new InMemoryCourse(courseMap);
        ArrayList<String> courseList = new ArrayList<>();
        courseList.add("a1_inst1_course1");
        courseList.add("a2_inst1_course1");
        courseList.add("tt1_inst1_course1");
        ((StudentUser) CurrentUser.getCurrentUser()).setCourses(courseList);

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
     */
    @Test
    void isStudentInCourse() {
        setUp(); // setting up static variables

        // initialize interactor and prereq objects
        InMemoryCourse courseGateway = new InMemoryCourse();
        InMemoryTaskMap taskGateway = new InMemoryTaskMap();
        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel responseModel) {
                assertEquals("course1", responseModel.getCourseID());

                ArrayList<String> courseTasks = new ArrayList<>();
                courseTasks.add("a1_inst1_course1");
                courseTasks.add("a2_inst1_course1");
                courseTasks.add("tt1_inst1_course1");
                assertEquals(courseTasks, responseModel.getTasks());
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
                courseGateway, taskGateway, outputBoundary);

        interactor.enrol(request);
    }

//    @Test
//    void getCourseTasks() {
//
//    }
//
//    @Test
//    void addTasksToTodolist() {
//
//    }

}
