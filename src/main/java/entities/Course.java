package entities;

import java.util.ArrayList;

/**
 * entity layer
 */
public class Course {
    /**
    class for Course entity with private instance variables
    using StudentUser, InstructorUser, and Task entities
     */
    private String courseName;
    private String courseInstructor;
    private ArrayList<String> students; // stores the IDs of students enrolled in the course
    private ArrayList<String> tasks;  // stores the IDs of the course's tasks
    private Boolean published;

    /**
     * Creates a new Course with a course name, instructor, and a list of tasks
     * @param courseName the name of the course
     * @param courseInstructor the name of the course instructor
     * @param tasks the tasks corresponding to the course
     */
    public Course(String courseName, String courseInstructor, ArrayList<String> tasks) {
        this.courseName = courseName;
        this.courseInstructor = courseInstructor;
        this.tasks = tasks;
        this.students = new ArrayList<String>();
        this.published = false;  // course creation, default set to not yet published
    }

    /**
    all getters
     */
    public String getCourseName() {
        return courseName;
    }
    public String getCourseInstructor() {
        return courseInstructor;
    }
    public ArrayList<String> getStudents() {
        return new ArrayList<String>(this.students);
    }
    public ArrayList<String> getTasks() {
        return new ArrayList<String>(this.tasks);
    }
    public Boolean getPublished() {
        return published;
    }

    /**
    all setters
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
    public void setStudents(ArrayList<String> students) {
        this.students = new ArrayList<String>(students);
    }
    public void setTasks(ArrayList<String> tasks) {
        this.tasks = new ArrayList<String>(tasks);
    }
    public void setPublished(Boolean published) {
        this.published = published;
    }
}
