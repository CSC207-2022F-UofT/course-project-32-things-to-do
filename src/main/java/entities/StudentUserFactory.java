package entities;

// Entity Layer

public class StudentUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new StudentUser(name, password);
    }

}
