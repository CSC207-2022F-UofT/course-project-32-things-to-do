package use_cases.course_features.course_enrolment_use_case;

import java.util.ArrayList;
import java.util.List;

// Use case layer

/**
 * The response model for course enrolment use case
 * the output data object
 */
public class CourseEnrolmentResponseModel {
//    String studentID; // adding student to course
    String courseID; // getting tasks, adding student
    ArrayList<String> tasks; // adding more tasks

    /**
     * The info that is stored in the database
     * The CourseMap will only be storing the IDs of enrolled StudentUsers
     */
    public CourseEnrolmentResponseModel(String courseID, List<String> tasks) {
        this.courseID = courseID;
        this.tasks = (ArrayList<String>) tasks;
    }

    public String getCourseID() {
        return courseID;
    }
    public List<String> getTasks() {
        return tasks;
    }
}
