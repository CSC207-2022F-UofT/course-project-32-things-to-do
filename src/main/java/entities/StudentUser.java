package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;

public class StudentUser {

    public StudentUser(String name, String password) {
    }

    public List<String> toDoList;

    public List<String> taskArchive;

    public List<String> courses;

    public ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList<String> notifications;

    public String name;

    public Map desiredGrades; // make it map Course to double once Course class is up

    public List<DateTimeFormatter> workingHours; // may need to change type
}
