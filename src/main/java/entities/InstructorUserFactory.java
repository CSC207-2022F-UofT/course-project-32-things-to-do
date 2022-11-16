package entities;

public class InstructorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new InstructorUser(name, password);
    }

}
