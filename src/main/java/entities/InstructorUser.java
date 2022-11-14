package entities;

import java.util.ArrayList;
import java.util.List;

public class InstructorUser implements User {

    /** Entity Layer
     * An Instructor User */

    public InstructorUser(String name, String password) {
        this.name = name;
        this.password = password;
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

    private ArrayList<String> Courses;

    public ArrayList<String> getCourses() {
        return this.Courses;
    }

    public void addCourse() {}
}
