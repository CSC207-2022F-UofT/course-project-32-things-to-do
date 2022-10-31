import java.util.ArrayList;

// entity layer

public class Course {
    /*
    class for Course entity with private instance variables
    uses StudentUser, InstructorUser, and Task entity classes
     */
    public String courseName;
    public String courseInstructor;
    public ArrayList<String> students; // should be ArrayList<StudentUser>
    private int numStudents;
    public ArrayList<String> tasks;  // should be ArrayList<Task>
    public Boolean publish;

    public Course(String courseName, ArrayList<String> tasks) {
        /* constructor that takes in two arguments:
            - the course name
            - a list of tasks
         */
        this.courseName = courseName;
        this.tasks = tasks;

        this.courseInstructor = "";
        this.students = new ArrayList<>(numStudents);
        this.numStudents = 0;
        this.publish = false;  // course creation, default set to not yet published
    }
}
