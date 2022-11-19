package Entities;

// Entity

public class UserWhisperer {

    /** Entity Layer
     * What this class does */

    public StudentUser createStudent(String name, String password) {
        return new StudentUser(name, password);
    }

    public InstructorUser createInstructor(String name, String password) {
        return null;
    }


    public String SearchUser(String name) {
        return null;
    }
}
