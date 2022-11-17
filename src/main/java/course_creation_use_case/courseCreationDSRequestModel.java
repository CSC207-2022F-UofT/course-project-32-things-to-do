package course_creation_use_case;

// Use case layer

// Notes:
// DONE?


import java.util.ArrayList;

public class courseCreationDSRequestModel {
    private final String courseName;
    private final String courseInstructor;
    private final String courseID;
    private ArrayList<String> tasks;

    public courseCreationDSRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.tasks = tasks;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getCourseInstructor() {
        return courseInstructor;
    }
    public String getCourseID() {
        return courseID;
    }
    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }
}
