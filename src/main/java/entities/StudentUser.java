package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentUser extends User {

    /** Entity Layer
     * A student User */

    public StudentUser(String name, String password) {
    }

    public List<String> toDoList;

    public List<String> taskArchive;

    public List<String> courses;

    public ArrayList inbox ; // once Invitation class is up, make it an ArrayList of Invitations

    public ArrayList<String> notifications;

    public String name;

    public Map desiredGrades; // make it map Course to double once Course class is up

    public List<List<LocalDateTime>> workingHours; // each internal list contains the start and end times
}
