// entity layer

public class Course {
    /*
    class for Course entity with private instance variables
    uses StudentUser, InstructorUser, and Task entity classes
     */
    public String courseName;
    public String courseInstructor;
    public String[] students;
    private int numStudents;
    public String[] tasks;
    public Boolean publish;

    public Course(String courseName, String[] tasks) {
        /* constructor that takes in two arguments:
            - the course name
            - a list of tasks
         */
        this.courseName = courseName;
        this.tasks = tasks;
        this.courseInstructor = "";
        this.students = new String[numStudents];
        this.numStudents = 0;
        this.publish = false;  // course creation, default set to not yet published
    }
}
