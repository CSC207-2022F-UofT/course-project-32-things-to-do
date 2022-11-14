package course_enrolment_and_creation_usecase;

// Use case layer

import java.util.ArrayList;

public class courseCreationDSRequestModel {
    private final String courseName;
    private final String courseInstructor;
    private final ArrayList<String> tasks;

    public courseCreationDSRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.tasks = tasks;
    }

    public String getCourseName() {
        return courseName;
    }

    //// finish up
}
