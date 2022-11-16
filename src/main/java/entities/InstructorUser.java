package entities;

import java.util.ArrayList;
import java.util.List;

public class InstructorUser implements User {

    /** Entity Layer
     * An Instructor User */

    public InstructorUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.courses = new ArrayList<String>();
    }

    private final String name;

    private final String password;

    private ArrayList<String> courses;

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

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public void addCourse(String course) {
        this.courses.add(course);
    }

}
