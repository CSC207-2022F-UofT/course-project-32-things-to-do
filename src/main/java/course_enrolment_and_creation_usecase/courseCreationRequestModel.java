package course_enrolment_and_creation_usecase;

// Use case layer

import java.util.ArrayList;

public class courseCreationRequestModel {
    private String courseName;
    private String courseInstructor;
    private ArrayList<String> tasks;

    public courseCreationRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.tasks = tasks;
    }

    String getCourseName() {
        return courseName;
    }

    void setCourseName() {
        this.courseName = courseName;
    }

    String getCourseInstructor() {
        return courseInstructor;
    }

    void setCourseInstructor() {
        this.courseInstructor = courseInstructor;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks() {
        this.tasks = tasks;
    }
}
