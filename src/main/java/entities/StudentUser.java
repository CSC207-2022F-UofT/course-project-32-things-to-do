package entities;

import javax.management.Notification;
import java.time.LocalTime;
import java.util.*;

public class StudentUser implements User {

    /** Entity Layer
     * A Student with a name, password, To Do List, Task Archive, Courses, Inbox, Notifications, and
     * desired grades.
     */

    public StudentUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.toDoList = new ArrayList<>();
        this.taskArchive = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.inbox = new ArrayList<Invitation>();
        this.notifications = new ArrayList<>();
        this.desiredGrades = new HashMap<>();
    }

    private final String name;

    private final String password;

    /**
     * @return the name of this StudentUser
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the password of this StudentUser
     */
    @Override
    public String getPass() {
        return password;
    }

    /**
     * @return whether the entered password is valid
     */
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
        this.courses.add(course);
    }

    public void setCourses(ArrayList<String> c) { this.courses = c; }

    private ArrayList<Invitation> inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList<Invitation> getInbox() {

        return this.inbox;
    }

    public void setInbox(ArrayList<Invitation> i) { this.inbox = i; }

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

    private ArrayList<LocalTime> workingHours;

    public ArrayList<LocalTime> getWorkingHours() {

        return this.workingHours;
    }

    public void setWorkingHours(ArrayList<LocalTime> hours) {
        this.workingHours = hours;
    }
}
