package entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public void addTaskToList(String task) {
        this.toDoList.add(task);
    }

    public void removeTaskFromList(String task) { this.toDoList.remove(task); }

    public void setToDoList(ArrayList<String> l) { this.toDoList = l; }

    private ArrayList<String> taskArchive;

    public ArrayList<String> getTaskArchive() {

        return this.taskArchive;
    }

    public void addTaskToArchive(String task) {
        this.taskArchive.add(task);
    }

    public void setTaskArchive(ArrayList<String> t) {
        this.taskArchive = t;
    }

    private ArrayList<String> courses;

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public void addCourse(String course) {
        this.toDoList.add(course);
    }

    public void setCourses(ArrayList<String> c) { this.courses = c; }

    private ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList getInbox() {

        return this.inbox;
    }

    public void addInvitation(String invite) {
        this.inbox.add(invite);
    }

    public void removeInvitation(String invite) {
        this.inbox.remove(invite);
    }

    public void setInbox(ArrayList i) { this.inbox = i; }

    private ArrayList<String> notifications;

    public ArrayList<String> getNotifications() {
        return this.notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    public void setNotifications(ArrayList<String> n) { this.notifications = n; }

    private Map<String,Double> desiredGrades; // String is course id

    public Map<String, Double> getDesiredGrades() {

        return this.desiredGrades;
    }

    public void addDesiredGrade(String course, Double grade) {
        this.desiredGrades.put(course, grade);
    }

    public void setDesiredGrades(Map<String, Double> m) {
        this.desiredGrades = m;
    }

    private ArrayList<LocalTime> workingHours; // each internal list contains the start and end times

    public ArrayList<LocalTime> getWorkingHours() {

        return this.workingHours;
    }

    public void setWorkingHours(ArrayList<LocalTime> hours) {
        this.workingHours = hours;
    }
}
