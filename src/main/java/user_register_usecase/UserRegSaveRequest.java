package user_register_usecase;

import entities.InstructorUser;
import entities.StudentUser;
import entities.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Use Case Layer

public class UserRegSaveRequest implements Serializable {

    private final String name;

    private String password;

    private ArrayList<String> toDoList;

    private ArrayList<String> taskArchive;

    private ArrayList<String> courses;

    private ArrayList inbox;

    private ArrayList<String> notifications;

    private HashMap<String, Double> desiredGrades;

    private ArrayList<LocalDateTime> workingHours;

    private final LocalDateTime creationTime;

    public UserRegSaveRequest(String name, String password, User user, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;

        if (user instanceof StudentUser) {
            this.toDoList = ((StudentUser) user).getToDoList();
            this.taskArchive = ((StudentUser) user).getTaskArchive();
            this.courses = ((StudentUser) user).getCourses();
            this.inbox = ((StudentUser) user).getInbox();
            this.notifications = ((StudentUser) user).getNotifications();
            this.desiredGrades = ((StudentUser) user).getDesiredGrades();
            this.workingHours = ((StudentUser) user).getWorkingHours();
        }

    }

    public String getName() { return name; }

    public String getPass() { return password; }

    public void setPass(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public ArrayList<String> getToDoList() {

        return this.toDoList;
    }

    public ArrayList<String> getTaskArchive() {

        return this.taskArchive;
    }

    public void addTaskToArchive(String task) {
        /**
         * Adds task to the task archive of this user. Does not remove the task from the to do list.
         */
        this.taskArchive.add(task);
    }

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public ArrayList getInbox() {

        return this.inbox;
    }

    public ArrayList<String> getNotifications() {
        return this.notifications;
    }

    public HashMap getDesiredGrades() {

        return this.desiredGrades;
    }

    public ArrayList<LocalDateTime> getWorkingHours() {

        return this.workingHours;
    }
}
