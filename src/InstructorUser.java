package entities;

import java.util.ArrayList;
import java.util.List;

public class InstructorUser extends User {

    /** Entity Layer
     * An Instructor User */

    public InstructorUser(String name, String password) {
    }

    private ArrayList<String> Courses;

    public ArrayList<String> getCourses() {
        return this.Courses;
    }

    public void addCourse() {}
}
