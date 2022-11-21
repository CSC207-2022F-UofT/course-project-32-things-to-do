package entities;

// Entity layer

public class InstructorUserFactory implements UserFactory {

    /**
     * @param name the name of this InstructorUser
     * @param password the password of this InstructorUser's account
     * @return a new InstructorUser
     */

    @Override
    public User create(String name, String password) {
        return new InstructorUser(name, password);
    }

}
