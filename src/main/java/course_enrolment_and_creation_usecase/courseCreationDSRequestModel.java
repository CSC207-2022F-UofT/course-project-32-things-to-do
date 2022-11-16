package course_enrolment_and_creation_usecase;

// Use case layer

// Notes:
// DONE?


import java.util.ArrayList;

public class courseCreationDSRequestModel {
    private final String courseName;
    private final String courseInstructor;
    private ArrayList<String> tasks;

    public courseCreationDSRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.tasks = tasks;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getCourseInstructor() {
        return courseInstructor;
    }
    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }
}
