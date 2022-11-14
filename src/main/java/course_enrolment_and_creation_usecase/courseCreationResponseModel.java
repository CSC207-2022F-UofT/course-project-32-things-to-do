package course_enrolment_and_creation_usecase;

import java.util.ArrayList;

// FIX THIS IS WRONG

public class courseCreationResponseModel {
    String courseName;
    String instructorName;
    ArrayList<String> tasks;

    public courseCreationResponseModel(String courseName, String instructorName) {
        this.courseName = courseName;
        this.instructorName = instructorName;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName() {
        this.courseName = courseName;
    }

    public String getInstructorName() {
        return this.instructorName;
    }

    public void setInstructorName() {
        this.instructorName = instructorName;
    }

    public ArrayList<String> getTasks() {
        return this.tasks;
    }

    public void setTasks() {
        this.tasks = tasks;
    }
}

