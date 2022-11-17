package course_creation_use_case;

import java.util.ArrayList;

// Use case layer

// Notes:
// the output data produced; returns the response as the output
// do NOT depend on anything NOR have any references to Entity objects: violates SRP

public class courseCreationResponseModel {
//    String courseName;
//    String instructorName;
    String courseID;
    ArrayList<String> tasks;

    public courseCreationResponseModel(String courseID, ArrayList<String> tasks) {
        this.courseID = courseID;
        this.tasks = tasks;
    }
    public String getCourseID() {
        return courseID;
    }
    public void setCourseID() {
        this.courseID = courseID;
    }
    public ArrayList<String> getTasks() {
        return tasks;
    }

    // do not think this is needed
//    public void setTasks() {
//        this.tasks = tasks;
//    }

//    public courseCreationResponseModel(String courseName, String instructorName) {
//        this.courseName = courseName;
//        this.instructorName = instructorName;
//    }
//
//    public String getCourseName() {
//        return this.courseName;
//    }
//
//    public void setCourseName() {
//        this.courseName = courseName;
//    }
//
//    public String getInstructorName() {
//        return this.instructorName;
//    }
//
//    public void setInstructorName() {
//        this.instructorName = instructorName;
//    }
//
//    public ArrayList<String> getTasks() {
//        return this.tasks;
//    }
//
//    public void setTasks() {
//        this.tasks = tasks;
//    }
}

