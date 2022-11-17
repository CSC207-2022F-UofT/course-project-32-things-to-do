package course_enrolment_use_case;

import java.util.ArrayList;

public class courseEnrolmentResponseModel {
    String studentID;
    ArrayList<String> tasks; // not sure if this goes here


    /**
     * The info that is stored in the database
     * The CourseMap will only be storing the IDs of enrolled StudentUsers
     * @param studentID the ID corresponding to the StudentUser
     * @param tasks the tasks of the course
     */
    public courseEnrolmentResponseModel(String studentID, ArrayList<String> tasks) {
        this.studentID = studentID;
        this.tasks = tasks;
    }

    public String getStudentID() {
        return studentID;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }
}
