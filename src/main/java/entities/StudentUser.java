package entities;

import java.time.LocalDateTime;
import java.util.*;

public class StudentUser extends User {

    /** Entity Layer
     * A student User */

    public StudentUser(String name, String password) {
        this.name = name;
        this.toDoList = new ArrayList<String>();
        this.taskArchive = new ArrayList<String>();
        this.courses = new ArrayList<String>();
        this.inbox = new ArrayList();
        this.notifications = new ArrayList<String>();
        this.desiredGrades = new HashMap<String, Double>();
        this.workingHours = new ArrayList<List<LocalDateTime>>();
    }

    private ArrayList<String> toDoList; // string being task ids

    public ArrayList<String> getToDoList() {

        return this.toDoList;
    }

    private ArrayList<String> taskArchive;

    public ArrayList<String> getTaskArchive() {

        return this.taskArchive;
    }

    private ArrayList<String> courses;

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public void setCourses(ArrayList<String> courses) {this.courses = courses;} //remove before merging!!

    private ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList getInbox() {

        return this.inbox;
    }

    private ArrayList<String> notifications;

    public ArrayList<String> getNotifications() {

        return this.notifications;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    private Map<String,Double> desiredGrades; // String is course id

    public Map<String, Double> getDesiredGrades() {

        return this.desiredGrades;
    }

    public void setDesiredGrades() {}

    private List<List<LocalDateTime>> workingHours; // each internal list contains the start and end times

    public List<List<LocalDateTime>> getWorkingHours() {

        return this.workingHours;
    }
}
