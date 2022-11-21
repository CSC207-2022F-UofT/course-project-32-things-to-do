package course_creation_use_case;

// Use case layer

/*
 Notes:
- requests what is needed for its input data (what person in front of computer enters)
- do NOT depend on anything NOR have any references to Entity objects: violates SRP
 */


import java.util.ArrayList;

public class CourseCreationRequestModel {
    private String courseName;
    private String courseInstructor;
    private final String courseID;
    private ArrayList<String> tasks;

    /**
     * Creates a request model with the given input
     * @param courseName the name of the course
     * @param courseInstructor the instructor of the course
     * @param tasks the task(s) associated with the course
     */
    public CourseCreationRequestModel(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.tasks = tasks;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName() {

        this.courseName = courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor() {

        this.courseInstructor = courseInstructor;
    }

    public String getCourseID() {
        return courseID;
    }

    public ArrayList<String> getTasks() {

        return tasks;
    }

    public void setTasks() {
        this.tasks = tasks;
    }
}
