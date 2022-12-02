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
    private String courseInstructor;
    private String courseID;
    private ArrayList<String> students; // stores the IDs of students enrolled in the course
    private ArrayList<String> tasks;  // stores the IDs of the course's tasks
//    private Boolean published;
    private boolean courseIsValid; // for unit testing

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
    public String getCourseInstructor() {
        return courseInstructor;
    }
    public String getCourseID() {
        return this.courseID;
    }

    public ArrayList<String> getStudents() {
        return new ArrayList<>(this.students);
    }
    /* add a new student id to the arraylist of student id strings, no return */
    public void addStudent(String studentID) {
        this.students.add(studentID);
    }

    public ArrayList<String> getTasks() {
        return new ArrayList<>(this.tasks);
    }
    /* new task added to course (input from instructor user) */
    public void addTask(String taskID) {
        this.tasks.add(taskID);
    }

//    public Boolean getPublished() {
//        return published;
//    }
    public boolean courseIsValid() {
        return (courseName != null && courseInstructor != null && !tasks.isEmpty());
    }

    /*
    setters
    don't think any setters are needed...
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public void setStudents(ArrayList<String> students) {
        this.students = new ArrayList<>(students);
    }
    public void setTasks(ArrayList<String> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Remove a single Task from tasks
     * @param task - the Task being removed
     */
    public void removeTask(Task task) {
        this.tasks.remove(task.getId());
    }
//    public void setPublished(Boolean published) {
//        this.published = published;
//    }
}
