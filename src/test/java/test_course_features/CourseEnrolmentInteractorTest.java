package test_course_features;

import entities.Course;
import entities.CurrentUser;
import entities.StudentUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import screens.course_features.InMemoryCourse;
import use_cases.course_features.course_creation_use_case.*;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentOutputBoundary;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentResponseModel;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the components of a course enrolment
 * which is basically every line of code in my interactor because clearly there are buuuuugs
 * acc maybe this is why i should be doing tests while i code oop
 */
public class CourseEnrolmentInteractorTest {
    @BeforeAll
    static void beforeAll() {
////        CurrentUser.setCurrentUser(new StudentUser("user1", "pwd"));
//        StudentUser student = new StudentUser("user1", "pwd");
//
//        ArrayList<String> courseTasks = new ArrayList<>();
//        courseTasks.add("1");
//        courseTasks.add("2");
//        courseTasks.add("3");
//        Course course = new Course("course1", "inst1", courseTasks);
//
//        HashMap<String, Course> courseMap = new HashMap<>();
//        courseMap.put("course1", course);
//
//        HashMap<String, StudentUser> studentMap = new HashMap<>();
//        studentMap.put("user1", student);
//
//        CourseEnrolmentDsGateway enrolmentGateway = new CourseEnrolmentDataAccess(courseMap, studentMap);
    }

    @Test
    void testing() {
        StudentUser student = new StudentUser("user1", "pwd");

        ArrayList<String> courseTasks = new ArrayList<>();
        courseTasks.add("1");
        courseTasks.add("2");
        courseTasks.add("3");
        Course course = new Course("course1", "inst1", courseTasks);

        HashMap<String, Course> courseMap = new HashMap<>();
        courseMap.put("course1", course);

        HashMap<String, StudentUser> studentMap = new HashMap<>();
        studentMap.put("user1", student);

        CourseEnrolmentOutputBoundary outputBoundary = new CourseEnrolmentOutputBoundary() {
            @Override
            public CourseEnrolmentResponseModel prepareSuccessView(CourseEnrolmentResponseModel newStudent) {
                ArrayList<String> courseTasks1 = new ArrayList<>();
                courseTasks1.add("1");
                courseTasks1.add("2");
                courseTasks1.add("3");
                Course course1 = new Course("course1", "inst1", courseTasks1);

                assertEquals("course1", course1.getCourseID());
                assertEquals("inst1", course1.getCourseName());
                assertEquals("course1inst1", course1.getCourseID());
                assertEquals(courseTasks1, course1.getTasks());
                assertNotNull(course1.getTasks());
//                assertTrue(enrolmentGateway.existsByCourseID(course1.getCourseID()));
//
//                assertTrue(enrolmentGateway.searchForCourse(course1.getCourseID()));
//                assertTrue(enrolmentGateway.existsStudentInCourse(course1.getCourseID()));
//
//                assertTrue(enrolmentGateway.saveStudentToCourse(course1.getCourseID()););


                // check coursetasks are made into the right ids (name_inst_course)

                // check if the ids are saved to an arraylist

                // check if the values from arraylist get the object associated with it,
                // and add to temp map (old task id map)

                // make 2 arraylists, old task ids, Tasks
                // check if key name is changed to name_stud_course, and add to new task ids arraylist

                // check if new task map with new id -> Tasks mapping is correct
                //

                // check if new task map is in tasksMap



                // check
                // check if student id is appended to course's 'students' parameter
//                assertTrue(enrolmentGateway.searchForCourse("course1").getStudents().contains("user1"));

                // student's to do list contains the course tasks
                return null;
            }

            @Override
            public CourseEnrolmentResponseModel prepareFailView(String error) {
                return null;
            }
        };


    }

    @Test
    void getCourseTasks() {

    }

    @Test
    void addTasksToTodolist() {

    }

}
