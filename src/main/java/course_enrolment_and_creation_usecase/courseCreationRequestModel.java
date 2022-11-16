package course_enrolment_and_creation_usecase;

// Use case layer

// Notes:
// DONE?
// requests what is needed for its input data
// do NOT depend on anything NOR have any references to Entity objects: violates SRP

import java.util.ArrayList;

public class courseCreationRequestModel {
    private String courseName;
    private String courseInstructor;
    private final String courseID;
    private ArrayList<String> tasks;

    public courseCreationRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.tasks = tasks;
    }

//    String getCourseName() {
//        return courseName;
//    }

    void setCourseName() {
        this.courseName = courseName;
    }

//    String getCourseInstructor() {
//        return courseInstructor;
//    }

    void setCourseInstructor() {
        this.courseInstructor = courseInstructor;
    }

    String getCourseID() {
        return courseID;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks() {
        this.tasks = tasks;
    }
}
