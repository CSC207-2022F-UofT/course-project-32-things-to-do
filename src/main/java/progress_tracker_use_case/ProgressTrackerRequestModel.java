package progress_tracker_use_case;

import Entities.Course;
import Entities.Task;
import Entities.User;

import java.util.HashMap;

/**
 * Request Model for the Progress Tracker Use Case
 * Acts as the input data object in the use case layer
 */

public class ProgressTrackerRequestModel {

    private final String studentUserID;

    private final String courseName;

    private HashMap<String, Task> allTasks;

    private HashMap<String, User> allUsers;

    private HashMap<String, Course> allCourses;

    private final String newGradeTaskName;

    private final String newGrade; //-1 for empty

    private final String newGoalGrade; //-1 for empty

    public ProgressTrackerRequestModel(String studentUserID, String courseName, HashMap<String, Task> allTasks,
                                       HashMap<String, User> allUsers, HashMap<String, Course> allCourses,
                                       String newGradeTaskName, String newGrade, String newGoalGrade) {
        this.studentUserID = studentUserID;
        this.courseName = courseName;
        this.allTasks = allTasks;
        this.allUsers = allUsers;
        this.allCourses = allCourses;
        this.newGradeTaskName = newGradeTaskName;
        this.newGrade = newGrade;
        this.newGoalGrade = newGoalGrade;
    }

    public String getStudentUserID() {
        return studentUserID;
    }

    public String getcourseName() {
        return courseName;
    }

    public HashMap<String, Task> getAllTasks() {
        return allTasks;
    }

    public void setAllTasks(HashMap<String, Task> allTasks) {
        this.allTasks = allTasks;
    }

    public HashMap<String, User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(HashMap<String, User> allUsers) {
        this.allUsers = allUsers;
    }

    public HashMap<String, Course> getAllCourses() {
        return allCourses;
    }

    public void setAllCourses(HashMap<String, Course> allCourses) {
        this.allCourses = allCourses;
    }

    public String getNewGradeTaskName() {
        return newGradeTaskName;
    }

    public String getNewGrade() {
        return newGrade;
    }

    public String getNewGoalGrade() {
        return newGoalGrade;
    }

}
