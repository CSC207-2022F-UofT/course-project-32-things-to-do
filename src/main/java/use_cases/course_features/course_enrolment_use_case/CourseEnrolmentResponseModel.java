package use_cases.course_features.course_enrolment_use_case;

import java.util.ArrayList;

// Use case layer

public class CourseEnrolmentResponseModel {
    String studentID; // part 1: adding student to course
    String courseID; // part 1
    ArrayList<String> tasks; // part 2: getting tasks from course


    /**
     * The info that is stored in the database
     * The CourseMap will only be storing the IDs of enrolled StudentUsers
     * @param studentID the ID corresponding to the StudentUser
     */
    public CourseEnrolmentResponseModel(String studentID, String courseID, ArrayList<String> tasks) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.tasks = tasks;
    }
    public String getStudentID() {
        return studentID;
    }
    public String getCourseID() {
        return courseID;
    }
    public ArrayList<String> getTasks() {
        return tasks;
    }
}
