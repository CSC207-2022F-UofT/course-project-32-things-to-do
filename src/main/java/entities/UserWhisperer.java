package entities;

// Entity

public class UserWhisperer {

    /** Entity Layer
     * What this class does */

    private StudentUser createStudent(String name, String password) {
        return new StudentUser(name, password);
    }

    private InstructorUser createInstructor(String name, String password) {
        return null;
    }


    private String SearchUser(String name) {
        return null;
    }
}
