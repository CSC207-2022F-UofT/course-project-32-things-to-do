package entities;

// Entity Layer

public class StudentUserFactory implements UserFactory {

    /**
     * @param name the name of this StudentUser
     * @param password the password of this StudentUser
     * @return a new StudentUser
     */
    @Override
    public User create(String name, String password) {
        return new StudentUser(name, password);
    }

}
