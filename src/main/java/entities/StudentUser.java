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

    private ArrayList<String> taskArchive;

    public ArrayList<String> getTaskArchive() {

        return this.taskArchive;
    }

    public void addTaskToArchive(String task) {
        /**
         * Adds task to the task archive of this user. Does not remove the task from the to do list.
         */
        this.taskArchive.add(task);
    }

    private ArrayList<String> courses;

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public void addCourse(String course) {
        this.toDoList.add(course);
    }

    private ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList getInbox() {

        return this.inbox;
    }

    public void addInvitation(String invite) {
        /**
         * Add invite to this user's inbox.
         */
        this.inbox.add(invite);
    }

    private ArrayList<String> notifications;

    public ArrayList<String> getNotifications() {
        return this.notifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    private HashMap<String,Double> desiredGrades; // String is course id

    public HashMap getDesiredGrades() {

        return this.desiredGrades;
    }

    public void addDesiredGrade(String course, Double grade) {
        this.desiredGrades.put(course, grade);
    }

    private ArrayList<LocalDateTime> workingHours; // each internal list contains the start and end times

    public ArrayList<LocalDateTime> getWorkingHours() {

        return this.workingHours;
    }

    public void setWorkingHours(ArrayList<LocalDateTime> hours) {
        this.workingHours = hours;
    }
}
