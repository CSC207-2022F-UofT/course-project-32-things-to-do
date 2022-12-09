package test_course_features.test_course_enrolment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import use_cases.course_features.course_enrolment_use_case.CourseEnrolmentRequestModel;


import static org.junit.jupiter.api.Assertions.*;



class CourseEnrolmentRequestModelTest {
    static CourseEnrolmentRequestModel enrolmentRequestModel;

    @BeforeAll
    static void beforeAll() {
        String courseName = "course1";
        String instructorName = "inst1";
        enrolmentRequestModel = new CourseEnrolmentRequestModel(courseName, instructorName);
    }

    @Test
    void getCourseName() {
        assertEquals("course1", enrolmentRequestModel.getCourseName());
    }

    @Test
    void getInstructorName() {
        assertEquals("inst1", enrolmentRequestModel.getCourseInstructor());
    }

    @Test
    void getCourseID() {
        assertEquals("course1inst1", enrolmentRequestModel.getCourseID());
    }
}
