package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * entity layer
 */

public class Course implements Serializable {
    /**
     * class for Course entity with private instance variables
     * using StudentUser, InstructorUser, and Task entities
     */
    private String courseName;
    private final String courseInstructor;
    private String courseID;
    private final ArrayList<String> students; // stores the IDs of students enrolled in the course
    private ArrayList<String> tasks;  // stores the IDs of the course's tasks

    /**
     * Creates a new Course with a course name, instructor name, and a list of tasks
     * @param courseName name of the course
     * @param courseInstructor name of the course instructor
     * @param tasks tasks corresponding to the course
     */

    public Course(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.courseID = courseName + courseInstructor;
        this.tasks = tasks;
        this.students = new ArrayList<>();
//        this.published = false;  // course creation, default set to not yet published
    }

    /*
    getters
     */
    public String getCourseName() {
        return courseName;
    }

    public String getCourseID() {
        return this.courseID;
    }

    public ArrayList<String> getStudents() {
        return new ArrayList<>(this.students);
    }

    public ArrayList<String> getTasks() {
        return this.tasks;
//        return new ArrayList<>(this.tasks);
    }

    public boolean courseIsValid() {
        return !(courseName.equals("") || courseInstructor.equals("") || tasks.isEmpty());
    }

    /*
    setters
    don't think any setters are needed...
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
}
