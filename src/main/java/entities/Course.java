package entities;

import java.util.List;
import java.util.ArrayList;

// entity layer

public class Course {
    /*
    class for Course entity with private instance variables
    uses StudentUser, InstructorUser, and Task entity classes
     */
    public String courseName;
    public String courseInstructor;
    public List<String> students; // will be storing the IDs of students
    private int numStudents;
    public List<String> tasks;  // will be storing the tasks IDs
    public Boolean publish;

    /**
     * Creates a new Course with a course name and a list of tasks
     * @param courseName the name of the course
     * @param tasks the tasks corresponding to the course
     */
    public Course(String courseName, List<String> tasks) {
        this.courseName = courseName;
        this.tasks = tasks;
        this.courseInstructor = "pgries";
        this.students = new ArrayList<>(numStudents);
        this.numStudents = 0;
        this.publish = false;  // course creation, default set to not yet published
    }
}
