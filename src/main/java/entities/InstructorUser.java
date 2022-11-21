package entities;

import java.util.ArrayList;
import java.util.List;

public class InstructorUser implements User {

    /** Entity Layer
     * An Instructor User with a name, password, and list of courses */

    public InstructorUser(String name, String password) {
        this.name = name;
        this.password = password;
        this.courses = new ArrayList<String>();
    }

    private final String name;

    private final String password;

    private ArrayList<String> courses;

    /**
     * @return the name of this InstructorUser
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the password of this InstructorUser's account
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

    public ArrayList<String> getCourses() {
        return this.courses;
    }

    public void addCourse(String course) {
        this.courses.add(course);
    }

    public void setCourses(ArrayList<String> c) { this.courses = c; };

}
