package use_cases.course_features.course_creation_use_case;

import java.util.ArrayList;

// Use case layer

/*
Notes:
- the output data produced; returns the response as the output
- does NOT depend on anything NOR should have any references to Entity objects: violates SRP
 */

public class CourseCreationResponseModel {
    String courseID;
    ArrayList<String> tasks;

    /**
     * Creates a response model for course creation use case
     * @param courseID the unique id of the course being created
     * @param tasks the task(s) associated with the course being created
     */
    public CourseCreationResponseModel(String courseID, ArrayList<String> tasks) {
        this.courseID = courseID;
        this.tasks = tasks;
    }
    public String getCourseID() {
        return courseID;
    }
    public ArrayList<String> getTasks() {
        return tasks;
    }
}

