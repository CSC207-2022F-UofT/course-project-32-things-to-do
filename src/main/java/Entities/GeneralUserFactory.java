package entities;

// Entity Layer

public class GeneralUserFactory implements UserFactory {

    /**
     * @param name the name of this StudentUser
     * @param password the password of this StudentUser
     * @return a new StudentUser
     */

    @Override
    public StudentUser createStudent(String name, String password) {
        return new StudentUser(name, password);
    }

    @Override
    public InstructorUser createInstructor(String name, String password) {
        return new InstructorUser(name, password);
    }

}
