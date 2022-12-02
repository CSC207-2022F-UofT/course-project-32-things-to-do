package entities;

public interface UserFactory {
    /**
     *
     * @param name the name of this user
     * @param password the password of this user
     * @return a User object
     */

    StudentUser createStudent(String name, String password);

    InstructorUser createInstructor(String name, String password);

}
