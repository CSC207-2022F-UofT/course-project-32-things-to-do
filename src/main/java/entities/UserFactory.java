package entities;


// Entity Layer

public interface UserFactory {
    /**
     * Creates Users with the specified name and password
     */
    StudentUser createStudent(String name, String password);

    InstructorUser createInstructor(String name, String password);
}
