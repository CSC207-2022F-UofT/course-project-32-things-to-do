package entities;

// Entity

public class UserWhisperer {

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
