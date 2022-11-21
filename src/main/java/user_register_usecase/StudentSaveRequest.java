package user_register_usecase;

import entities.StudentUser;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentSaveRequest extends UserRegSaveRequest {

    private String name;

    private String password;

    private LocalDateTime creationTime;

    private final ArrayList<String> toDoList;

    private final ArrayList<String> taskArchive;

    private final ArrayList<String> courses;

    private final ArrayList inbox;

    private final ArrayList<String> notifications;

    private final Map<String, Double> desiredGrades;

    private final ArrayList<LocalTime> workingHours;


    public StudentSaveRequest(String name, String password, StudentUser student, LocalDateTime creationTime) {
        super(name, password, student, creationTime);
        this.toDoList = student.getToDoList();
        this.taskArchive = student.getTaskArchive();
        this.courses = student.getCourses();
        this.inbox = student.getInbox();
        this.notifications = student.getNotifications();
        this.desiredGrades = student.getDesiredGrades();
        this.workingHours = student.getWorkingHours();
    }

    public ArrayList<String> getToDoList() {

        return this.toDoList;
    }

    public ArrayList<String> getTaskArchive() {

        return this.taskArchive;
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

    public Map<String, Double> getDesiredGrades() {

        return this.desiredGrades;
    }

    public ArrayList<LocalTime> getWorkingHours() {

        return this.workingHours;
    }

}
