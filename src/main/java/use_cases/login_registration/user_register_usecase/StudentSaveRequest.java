package use_cases.login_registration.user_register_usecase;

import entities.Invitation;
import entities.StudentUser;

import javax.management.Notification;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

public class StudentSaveRequest extends UserRegSaveRequest {
    /**
     * A transient data storage class that contains the same information as a StudentUser
     */

    private final String name;

    private final String password;

    private final LocalDateTime creationTime;

    private final ArrayList<String> toDoList;

    private final ArrayList<String> taskArchive;

    private final ArrayList<String> courses;

    private final ArrayList<Invitation> inbox;

    private final ArrayList<String> notifications;

    private final Map<String, Double> desiredGrades;

    private final ArrayList<LocalTime> workingHours;

    /**
     * @param name the name of this StudentUser
     * @param password the password of this StudentUser
     * @param student the StudentUser that needs to be saved
     * @param creationTime the time at which this StudentUser was created or saved
     */
    public StudentSaveRequest(String name, String password, StudentUser student, LocalDateTime creationTime) {
        super(name, password, student, creationTime);
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
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

    public ArrayList<Invitation> getInbox() {

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

    @Override
    public StudentUser initializeUser() {
        StudentUser s = new StudentUser(this.name, this.password);
        s.setCourses(this.courses);
        s.setToDoList(this.toDoList);
        s.setTaskArchive(this.getTaskArchive());
        s.setInbox(this.getInbox());
        s.setNotifications(this.getNotifications());
        s.setDesiredGrades(this.getDesiredGrades());
        s.setWorkingHours(this.getWorkingHours());
        return s;
    }

}
