package use_cases.course_features.course_enrolment_use_case;

import java.util.ArrayList;

public class CourseEnrolmentResponseModel {
    String studentID;
//    ArrayList<String> tasks; // not sure if this goes here


    /**
     * The info that is stored in the database
     * The CourseMap will only be storing the IDs of enrolled StudentUsers
     * @param studentID the ID corresponding to the StudentUser
     */
    public CourseEnrolmentResponseModel(String studentID) {
        this.studentID = studentID;
//        this.tasks = tasks;
    }

    public String getStudentID() {
        return studentID;
    }

//    public ArrayList<String> getTasks() {
//        return tasks;
//    }
}