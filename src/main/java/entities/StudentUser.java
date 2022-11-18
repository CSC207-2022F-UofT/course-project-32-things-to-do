package Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentUser extends User {

    /** Entity Layer
     * A student User */

    public StudentUser(String name, String password) {
    }

    private ArrayList<String> toDoList;

    public ArrayList<String> getToDoList() {
        return null;
    }

    private ArrayList<String> taskArchive;

    public ArrayList<String> getTaskArchive() {
        return null;
    }

    private ArrayList<String> courses;

    public ArrayList<String> getCourses() {
        return null;
    }

    private ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList getInbox() {
        return null;
    }

    private ArrayList<String> notifications;

    public ArrayList<String> getNotifications() {
        return null;
    }

    private String name;

    public String getName() {
        return this.name;
    }

    private Map desiredGrades; // make it map Course to double once Course class is up

    public Map getDesiredGrades() {
        return null;
    }

    private List<List<LocalDateTime>> workingHours; // each internal list contains the start and end times

    public List<List<LocalDateTime>> getWorkingHours() {
        return null;
    }
}
