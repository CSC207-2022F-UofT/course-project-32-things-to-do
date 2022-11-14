package entities;

import java.time.LocalDateTime;
import java.util.*;

public class StudentUser implements User {

    /** Entity Layer
     * A student User */

    public StudentUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.toDoList = new ArrayList<String>();
        this.taskArchive = new ArrayList<String>();
        this.courses = new ArrayList<String>();
        this.inbox = new ArrayList();
        this.notifications = new ArrayList<String>();
        this.desiredGrades = new HashMap<String, Double>();
        this.workingHours = new ArrayList<List<LocalDateTime>>();
    }

    private final String name;

    private final String password;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPass() {
        return password;
    }

    @Override
    public boolean checkPassword() {
        return password != null && password.length() > 8;
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

    private ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList getInbox() {

        return this.inbox;
    }

    private ArrayList<String> notifications;

    public ArrayList<String> getNotifications() {

        return this.notifications;
    }

    private Map<String,Double> desiredGrades; // String is course id

    public Map getDesiredGrades() {

        return this.desiredGrades;
    }

    public void setDesiredGrades() {}

    private List<List<LocalDateTime>> workingHours; // each internal list contains the start and end times

    public List<List<LocalDateTime>> getWorkingHours() {

        return this.workingHours;
    }
}
